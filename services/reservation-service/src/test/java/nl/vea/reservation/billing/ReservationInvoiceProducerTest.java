package nl.vea.reservation.billing;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MediaType;
import nl.vea.reservation.reservation.entities.Reservation;
import org.awaitility.Awaitility;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.given;
import static nl.vea.reservation.rest.ReservationResource.STANDARD_RATE_PER_DAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@ApplicationScoped
@TestProfile(ReservationInvoiceProducerTest.RabbitMQTest.class)
public class ReservationInvoiceProducerTest {

    public static final class RabbitMQTest implements QuarkusTestProfile {}

    private final Map<Integer, Invoice> receivedInvoices = new HashMap<>();
    private final AtomicInteger ids = new AtomicInteger(0);

    @Incoming("invoices-rabbitmq")
    public void processInvoice(JsonObject json){
        Invoice invoice = json.mapTo(Invoice.class);
        Log.infof("Processing the received invoice: %s", invoice);
        receivedInvoices.put(ids.incrementAndGet(), invoice);
    }

    @Test
    public void testInvoiceProduced(){

        //given
        Reservation reservation = new Reservation();
        reservation.setStartDay(LocalDate.now().plusDays(1));
        reservation.setEndDay(reservation.getStartDay());
        reservation.setCarId(399L);

        // when
        given().body(reservation).contentType(MediaType.APPLICATION_JSON)
                .when().post("/reservation")
                .then().statusCode(200);

        Awaitility.await().atMost(10, TimeUnit.SECONDS)
                .until(() -> receivedInvoices.size() == 1);

        // then
        assertEquals(1, receivedInvoices.size());
        assertEquals(STANDARD_RATE_PER_DAY, receivedInvoices.get(1).getPrice());
    }

}
