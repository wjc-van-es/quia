package nl.vea.rental.rest;

import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.vea.rental.entities.ErrorResponse;
import nl.vea.rental.entities.Rental;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/rental")
public class RentalResource {

    @Path("/start/{userId}/{reservationId}")
    @POST
    public Rental start(String userId, Long reservationId){
        Log.infof("Starting rental for %s with reservation %s.", userId, reservationId);
        var rental =  new Rental(userId, reservationId, LocalDate.now());
        rental.persist();
        Log.infof("Persisting: %s.", rental);
        return rental;
    }

    @Path("/end/{userId}/{reservationId}")
    @PUT
    public Rental end(String userId, Long reservationId){
        Log.infof("Ending rental for %s with reservation %s.", userId, reservationId);
        var optional = Rental.findByUserAndReservationIdsOptional(userId, reservationId);
        var rental = optional.orElseThrow(
                () -> new NotFoundException(String.format("Rental with userId %s and reservationId %s is not found",
                        userId, reservationId)));
        rental.setEndDate(LocalDate.now());
        rental.setActive(false);
        rental.update();
        Log.infof("Updating: %s.", rental);
        return rental;
    }

    @GET
    public List<Rental> list(){
        return Rental.listAll();
    }

    @GET
    @Path("/active")
    public List<Rental> listActive(){
        return Rental.listActive();
    }

    // small improvement instead of no response body we get a String with the message, but no proper json content yet
    // this appears to be more of a hassle than in Spring
    // https://marcelkliemannel.com/articles/2021/centralized-error-handling-and-a-custom-error-page-in-quarkus/
    // Maybe this is a good case for pattern matching
    @ServerExceptionMapper
    public Response mapException(WebApplicationException e){
        var status = switch (e) {
            case BadRequestException ex -> RestResponse.Status.BAD_REQUEST;
            case NotAuthorizedException ex -> RestResponse.Status.UNAUTHORIZED;
            case NotAllowedException ex -> RestResponse.Status.METHOD_NOT_ALLOWED;
            case NotAcceptableException ex -> RestResponse.Status.NOT_ACCEPTABLE;
            case NotFoundException ex -> RestResponse.Status.NOT_FOUND;
            case NotSupportedException ex -> RestResponse.Status.UNSUPPORTED_MEDIA_TYPE;
            case RedirectionException ex -> RestResponse.Status.INTERNAL_SERVER_ERROR;
            case InternalServerErrorException ex -> RestResponse.Status.INTERNAL_SERVER_ERROR;
            case ServiceUnavailableException ex -> RestResponse.Status.SERVICE_UNAVAILABLE;
            default -> throw new IllegalStateException("Unexpected value: " + e);
        };
        var notFoundMessage = new ErrorResponse(status.getStatusCode(),
                status.getReasonPhrase(), e.getMessage());
        return
                Response.fromResponse(e.getResponse())
                        .status(status)
                        .entity(notFoundMessage)
                        .build();
    }
}
