package nl.vea.reservation.rest;

import io.quarkus.logging.Log;
import io.smallrye.graphql.client.GraphQLClient;
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
    public Collection<Car> availability(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate){
        List<Car> availableCars = inventoryClient.allCars();
        Map<Long, Car> carsById = new HashMap<>();
        for (Car car: availableCars){
            carsById.put(car.getId(), car);
        }

        List<Reservation> reservations = Reservation.listAll();
        for (Reservation reservation: reservations){
            if (reservation.isReserved(startDate, endDate)){
                carsById.remove(reservation.getCarId());
            }
        }
        return carsById.values();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Transactional
    public Reservation make(Reservation reservation) {
        reservation.setUserId(currentUserId());

        reservation.persist();

        Log.infof("Saved reservation %s", reservation);
        if (reservation.getStartDay().isEqual(LocalDate.now())) {
            var rental = rentalClient.start(reservation.getUserId(), reservation.getId());
            Log.infof("Received confirmation of rental %s", rental);
        }

        return reservation;
    }

    @GET
    @Path("all")
    public List<Reservation> allReservations(){
        return Reservation.<Reservation>streamAll() //generics necessary to resolve reservation.getUserId()
                .filter(reservation -> currentUserId().equals(reservation.getUserId()))
                .toList();
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
