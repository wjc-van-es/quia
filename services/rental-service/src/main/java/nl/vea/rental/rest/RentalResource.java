package nl.vea.rental.rest;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.vea.rental.billing.InvoiceAdjust;
import nl.vea.rental.entities.ErrorResponse;
import nl.vea.rental.entities.Rental;
import nl.vea.rental.reservation.ReservationClient;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/rental")
public class RentalResource {

    public static final double STANDARD_REFUND_RATE_PER_DAY = -10.99;
    public static final double STANDARD_PRICE_FOR_PROLONGED_DAY = 25.99;

    private final AtomicLong id = new AtomicLong(0);

    @Inject
    @RestClient
    ReservationClient reservationClient;

    @Inject
    @Channel("invoices-adjust")
    Emitter<InvoiceAdjust> adjustmentEmitter;


    @PUT
    @Path("test/invoice-adjust/{reservationEndDate}")
    public InvoiceAdjust testInvoiceAdjustProducer(LocalDate reservationEndDate) {
        double price = computePrice(reservationEndDate, LocalDate.now());
        var invoiceAdjust = new InvoiceAdjust(Long.toString(id.incrementAndGet()),
                "anonymous", LocalDate.now(), price);
        Log.infof("Test sending invoiceAdjust: %s", invoiceAdjust);
        adjustmentEmitter.send(invoiceAdjust);
        return invoiceAdjust;
    }

    @Path("/start/{userId}/{reservationId}")
    @POST
    public Rental start(String userId, Long reservationId) {
        Log.infof("Starting rental for %s with reservation %s.", userId, reservationId);

        Optional<Rental> optional = Rental.findByUserAndReservationIdsOptional(userId, reservationId);
        Rental rental;
        if (optional.isPresent()) {
            // a confirmed invoice has already been received
            rental = optional.get();
            rental.setActive(true);
            rental.update();
            Log.infof("Updating: %s.", rental);
        } else {
            // rental starting right now before payment
            rental = new Rental(userId, reservationId, LocalDate.now());
            rental.setActive(true);
            rental.persist();
            Log.infof("Persisting: %s.", rental);
        }

        return rental;
    }

    @Path("/end/{userId}/{reservationId}")
    @PUT
    public Rental end(String userId, Long reservationId) {
        Log.infof("Ending rental for %s with reservation %s.", userId, reservationId);
        var optional = Rental.findByUserAndReservationIdsOptional(userId, reservationId);
        var rental = optional.orElseThrow(
                () -> new NotFoundException(String.format("Rental with userId %s and reservationId %s is not found",
                        userId, reservationId)));

        if (!rental.isPaid()) {
            Log.warn("Rental is not paid: " + rental);
            // trigger error processing
        }

        var reservation = reservationClient.getById(reservationId);
        var today = LocalDate.now();
        if (!reservation.getEndDay().isEqual(today)) {
            Log.infof("Adjusting the price for rental %s.\nOriginal reservation end day was %s.",
                    rental, reservation.getEndDay());
            adjustmentEmitter.send(new InvoiceAdjust(rental.id.toString(), userId, today,
                    computePrice(reservation.getEndDay(), today)));
        }

        rental.setEndDate(LocalDate.now());
        rental.setActive(false);
        rental.update();
        Log.infof("Updating: %s.", rental);
        return rental;
    }

    @GET
    public List<Rental> list() {
        return Rental.listAll();
    }

    @GET
    @Path("/active")
    public List<Rental> listActive() {
        return Rental.listActive();
    }

    // small improvement instead of no response body we get a String with the message, but no proper json content yet
    // this appears to be more of a hassle than in Spring
    // https://marcelkliemannel.com/articles/2021/centralized-error-handling-and-a-custom-error-page-in-quarkus/
    // Maybe this is a good case for pattern matching
    @ServerExceptionMapper
    public Response mapException(WebApplicationException e) {
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

    private double computePrice(LocalDate endDate, LocalDate today) {
        return endDate.isBefore(today) ?
                ChronoUnit.DAYS.between(endDate, today)
                        * STANDARD_PRICE_FOR_PROLONGED_DAY :
                ChronoUnit.DAYS.between(today, endDate)
                        * STANDARD_REFUND_RATE_PER_DAY;
    }
}
