package nl.vea.reservation.resources;

import io.restassured.RestAssured;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import nl.vea.reservation.reservation.Reservation;
import nl.vea.reservation.rest.ReservationResource;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class ReservationResourceTest {

    @TestHTTPEndpoint(ReservationResource.class)
    @TestHTTPResource
    URL reservationResource;

    @Test
    public void testReservationIds() {
        // given
        Reservation reservation = new Reservation();
        reservation.setStartDay(LocalDate.now().plus(5, ChronoUnit.DAYS));
        reservation.setEndDay(LocalDate.now().plus(12, ChronoUnit.DAYS));
        reservation.setCarId(384L);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(reservation)
                .when()
                .post(reservationResource)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }
}
