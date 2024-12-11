package nl.vea.quia.users;

import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import nl.vea.quia.users.model.Car;
import nl.vea.quia.users.model.Reservation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Path("/")
public class ReservationsResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance index(
                LocalDate startDate,
                LocalDate endDate,
                String name);

        public static native TemplateInstance listofreservations(List<Reservation> reservations);

        public static native TemplateInstance availablecars(
                List<Car> cars, LocalDate startDate, LocalDate endDate);
    }

    @Inject
    SecurityContext securityContext;

    @RestClient
    ReservationsClient client;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate){
        if (Objects.isNull(startDate)){
            startDate = LocalDate.now().plusDays(1L);
        }
        if (Objects.isNull(endDate)){
            endDate = LocalDate.now().plusDays(4L);
        }
        return Templates.index(startDate, endDate, currentUserId());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/get")
    public TemplateInstance getReservations(){
        List<Reservation> reservations = client.allReservations();
        return Templates.listofreservations(reservations);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/available")
    public TemplateInstance getAvailableCars(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate){
        List<Car> availableCars = client.availability(startDate, endDate);
        return Templates.availablecars(availableCars, startDate, endDate);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/reserve")
    public RestResponse<TemplateInstance> create(@RestForm LocalDate startDate, @RestForm LocalDate endDate,
                                                 @RestForm Long carId){
        Reservation reservation = new Reservation();
        reservation.setCarId(carId);
        reservation.setStartDay(startDate);
        reservation.setEndDay(endDate);
        Reservation result = client.make(reservation);
        Log.infof("Received confirmation of a new reservation: %s", result);
        return RestResponse
                .ResponseBuilder
                .ok(getReservations())
                .header("HX-Trigger-After-Swap", "update-available-cars-list")
                .build();
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
