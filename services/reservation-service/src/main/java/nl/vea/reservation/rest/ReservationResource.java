package nl.vea.reservation.rest;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import nl.vea.reservation.inventory.Car;
import nl.vea.reservation.inventory.GraphQLInventoryClient;
import nl.vea.reservation.inventory.InventoryClient;
import nl.vea.reservation.rental.RentalClient;
import nl.vea.reservation.reservation.entities.Reservation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {
    private final InventoryClient inventoryClient;
    private final RentalClient rentalClient;

    @Inject
    private SecurityContext securityContext;

    public ReservationResource(@GraphQLClient("inventory") GraphQLInventoryClient inventoryClient,
                               @RestClient RentalClient rentalClient) {
        this.inventoryClient = inventoryClient;
        this.rentalClient = rentalClient;
    }

    @GET
    @Path("availability")
    public Uni<Collection<Car>> availability(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate){
        Uni<List<Car>> availableCarsUni = inventoryClient.allCars();
        Uni<List<Reservation>> reservationsUni = Reservation.listAll();

        return Uni.combine().all().unis(availableCarsUni, reservationsUni)
                .with((availableCars, reservations) -> {
                    long[] reservedCarIds = reservations.stream()
                            .filter(reservation -> reservation.isReserved(startDate, endDate))
                                    .mapToLong(Reservation::getCarId)
                                            .toArray();
                    return availableCars.stream()
                            // filter only cars whose ids are not in reservedCarIds are available
                            .filter(car -> Arrays.binarySearch(reservedCarIds, car.getId()) < 0)
                            .toList();

                });
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @WithTransaction
    public Uni<Reservation> make(Reservation reservation) {
        reservation.setUserId(currentUserId());

        return reservation.<Reservation>persist().onItem()
                .call(persistedReservation -> {
                    Log.infof("Saved reservation %s", persistedReservation);
                    if (persistedReservation.getStartDay().isEqual(LocalDate.now())) {
                        return rentalClient.start(persistedReservation.getUserId(), persistedReservation.getId())
                                .onItem().invoke(rental ->
                                        Log.infof("Received confirmation of rental %s", rental))
                                .replaceWith(persistedReservation); // this doesn't work
                    }
                    return Uni.createFrom().item(persistedReservation); // this works
                }

        );
    }

    @GET
    @Path("all")
    public Uni<List<Reservation>> allReservations(){
        return Reservation.<Reservation>listAll()
            .onItem()
            .transform(reservations -> reservations
                .stream()
                .filter(reservation -> currentUserId().equals(reservation.getUserId()))
                .toList());
    }

    private String currentUserId() {
        Principal userPrincipal = securityContext.getUserPrincipal();
        if(Objects.nonNull(userPrincipal)){
            return userPrincipal.getName();
        } else {
           return "anonymous";
        }
    }
}
