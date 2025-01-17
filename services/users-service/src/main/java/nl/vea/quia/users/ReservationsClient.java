package nl.vea.quia.users;

import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import nl.vea.quia.users.model.Car;
import nl.vea.quia.users.model.Reservation;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.util.List;

@RegisterRestClient(configKey = "reservations")
@AccessToken
@Path("reservation")
public interface ReservationsClient {

    @GET
    @Path("all")
    List<Reservation> allReservations();

    @POST
    Reservation make(Reservation reservation);

    @GET
    @Path("availability")
    List<Car> availability(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate);
}
