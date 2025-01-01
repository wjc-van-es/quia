package nl.vea.rental.rest;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kafka.InjectKafkaCompanion;
import io.quarkus.test.kafka.KafkaCompanionResource;
import io.smallrye.reactive.messaging.kafka.companion.ConsumerTask;
import io.smallrye.reactive.messaging.kafka.companion.KafkaCompanion;
import nl.vea.rental.reservation.Reservation;
import nl.vea.rental.reservation.ReservationClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@QuarkusTestResource(KafkaCompanionResource.class)
public class RentalResourceTest {

    @InjectKafkaCompanion
    KafkaCompanion kafkaCompanion;

    @Test
    public void testRentalProlongedInvoiceSend(){
        // Given stub the ReservationClient call
        Reservation reservation = new Reservation();
        reservation.setEndDay(LocalDate.now().minusDays(1));

        ReservationClient mock = Mockito.mock(ReservationClient.class);
        Mockito.when(mock.getById(1L)).thenReturn(reservation);
        QuarkusMock.installMockForType(mock, ReservationClient.class,
                RestClient.LITERAL);

        // start new rental for reservation with id 1
        given()
                .when().post("/rental/start/user123/1")
                .then().statusCode(200);

        // when the rental ends with one prolonged day
        given()
                .when().put("/rental/end/user123/1")
                .then().statusCode(200)
                .body("active", is(false),
                        "endDate", is(LocalDate.now().toString()));

        // then a invoiceAdjust message is send to the "invoice-adjust" Kafka topic
        // Note this doesn't work in combination with nl.vea.rental.billing.AdjustedInvoiceMonitor.monitor
        // apparently, when a message is consumed from the topic by another consumer no next record is available
        // https://smallrye.io/smallrye-reactive-messaging/latest/kafka/test-companion/
        ConsumerTask<String, String> invoiceAdjust = kafkaCompanion
                .consumeStrings()
                .fromTopics("invoices-adjust", 1)
                .awaitNextRecord(Duration.ofSeconds(10));

        assertEquals(1L, invoiceAdjust.stream().count());
        var value = invoiceAdjust.getFirstRecord().value();
        Log.infof("Value of message from 'invoices-adjust' topic:\n%s", value);
        assertTrue(value
                .contains("\"price\":" +
                        RentalResource.STANDARD_PRICE_FOR_PROLONGED_DAY));
    }
}
