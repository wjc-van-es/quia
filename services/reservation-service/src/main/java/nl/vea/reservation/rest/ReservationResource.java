package nl.vea.reservation.rest;

import io.quarkus.logging.Log;
import io.smallrye.graphql.client.GraphQLClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import nl.vea.reservation.inventory.Car;
import nl.vea.reservation.inventory.GraphQLInventoryClient;
import nl.vea.reservation.inventory.InventoryClient;
import nl.vea.reservation.rental.RentalClient;
import nl.vea.reservation.reservation.Reservation;
import nl.vea.reservation.reservation.ReservationsRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {
    private final ReservationsRepository reservationsRepository;
    private final InventoryClient inventoryClient;
    private final RentalClient rentalClient;

    @Inject
    private SecurityContext securityContext;

    public ReservationResource(ReservationsRepository reservationsRepository,
                               @GraphQLClient("inventory") GraphQLInventoryClient inventoryClient,
                               @RestClient RentalClient rentalClient) {
        this.reservationsRepository = reservationsRepository;
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

        List<Reservation> reservations = reservationsRepository.findAll();
        for (Reservation reservation: reservations){
            if (reservation.isReserved(startDate, endDate)){
                carsById.remove(reservation.getCarId());
            }
        }
        return carsById.values();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Reservation make(Reservation reservation) {
        reservation.setUserId(currentUserId());

        Reservation result = reservationsRepository.save(reservation);
        Log.infof("Saved reservation %s", result);
        // Dummy value for now
        //String userId = "Uncle_X";
        if (result.getStartDay().isEqual(LocalDate.now())) {
            var rental = rentalClient.start(result.getUserId(), result.getId());
            Log.infof("Received confirmation of rental %s", rental);
        }

        return result;
    }

    @GET
    @Path("all")
    public List<Reservation> allReservations(){
        return reservationsRepository.findAll()
                .stream()
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
