package nl.vea.reservation.rest;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import nl.vea.reservation.billing.Invoice;
import nl.vea.reservation.inventory.Car;
import nl.vea.reservation.inventory.GraphQLInventoryClient;
import nl.vea.reservation.inventory.InventoryClient;
import nl.vea.reservation.rental.RentalClient;
import nl.vea.reservation.reservation.entities.Reservation;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {
    public static final double STANDARD_RATE_PER_DAY = 19.99;

    private final InventoryClient inventoryClient;
    private final RentalClient rentalClient;

    @Inject
    private SecurityContext securityContext;

    @Inject
    @Channel("invoices")
    MutinyEmitter<Invoice> invoiceEmitter;

    public ReservationResource(@GraphQLClient("inventory") GraphQLInventoryClient inventoryClient,
                               @RestClient RentalClient rentalClient) {
        this.inventoryClient = inventoryClient;
        this.rentalClient = rentalClient;
    }

    @Retry(maxRetries = 25, delay = 1000)
    @Fallback(fallbackMethod = "availabilityFallback")
    @GET
    @Path("availability")
    public Uni<List<Car>> availability(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate){
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


    public Uni<List<Car>> availabilityFallback(LocalDate startDate, LocalDate endDate){
        return Uni.createFrom().item(List.of());
    }

//    public InventoryClient getInventoryClient() {
//        return inventoryClient;
//    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @WithTransaction
    public Uni<Reservation> make(Reservation reservation) {
        reservation.setUserId(currentUserId());

        return reservation.<Reservation>persist().onItem()
                .call(persistedReservation -> {
                    Log.infof("Saved reservation %s", persistedReservation);

                    Uni<Void> invoiceUni = invoiceEmitter
                            .send(new Invoice(reservation, computePrice(reservation)))
                            .onFailure()
                            .invoke(throwable -> Log.errorf("Couldn't create the invoice for %s. %s%n",
                                    persistedReservation, throwable));

                    if (persistedReservation.getStartDay().isEqual(LocalDate.now())) {
                        return invoiceUni.chain(() ->
                                rentalClient.start(persistedReservation.getUserId(), persistedReservation.getId())
                                .onItem().invoke(rental ->
                                        Log.infof("Received confirmation of rental %s", rental))
                                .replaceWith(persistedReservation));
                    }
                    return invoiceUni.replaceWith(persistedReservation);
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

    @GET
    @Path("{id}")
    public Uni<Reservation> getById(Long id){
        return Reservation.findById(id);
    }

    private String currentUserId() {
        Principal userPrincipal = securityContext.getUserPrincipal();
        if(Objects.nonNull(userPrincipal)){
            return userPrincipal.getName();
        } else {
           return "anonymous";
        }
    }

    private double computePrice(Reservation reservation) {
        return (ChronoUnit.DAYS.between(reservation.getStartDay(), reservation.getEndDay()) + 1) * STANDARD_RATE_PER_DAY;
    }
}
