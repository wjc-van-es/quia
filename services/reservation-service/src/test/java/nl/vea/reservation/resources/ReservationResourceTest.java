package nl.vea.reservation.resources;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.DisabledOnIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import nl.vea.reservation.inventory.Car;
import nl.vea.reservation.reservation.entities.Reservation;
import nl.vea.reservation.rest.ReservationResource;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ReservationResourceTest {

    @TestHTTPEndpoint(ReservationResource.class)
    @TestHTTPResource
    URL reservationResource;

    @TestHTTPEndpoint(ReservationResource.class)
    @TestHTTPResource("availability")
    URL availability;


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

    @DisabledOnIntegrationTest(forArtifactTypes = DisabledOnIntegrationTest.ArtifactType.ALL)
    @Test
    public void testWhenCarIsReservedThenNoLongerAvailable(){
        // Given: inventory contains single car
        // If You use the Mockito mock then first disable nl.vea.reservation.inventory.MockInventoryClient
//        GraphQLInventoryClient mock = Mockito.mock(GraphQLInventoryClient.class);
//        Car peugeot = new Car(1L, "SPQR001", "Peugeot", "206");
//        Mockito.when(mock.allCars()).thenReturn(Collections.singletonList(peugeot));
//        QuarkusMock.installMockForType(mock, GraphQLInventoryClient.class);

        String startDate = "2022-01-01";
        String endDate = "2022-01-10";

        // List available cars for our requested timeslot and choose one
        Car[] cars = RestAssured.given()
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when().get(availability)
                .then().statusCode(200)
                .extract().as(Car[].class);
        assertNotNull(cars);
        assertEquals(1, cars.length);

        Car car = cars[0];

        //Prepare the reservation
        Reservation reservation = new Reservation();
        reservation.setCarId(car.getId());
        reservation.setStartDay(LocalDate.parse(startDate));
        reservation.setEndDay(LocalDate.parse(endDate));

        // when submitting the reservation
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(reservation)
                .when()
                .post(reservationResource)
                .then()
                .statusCode(200)
                .body("carId", is(car.getId().intValue())); // then carId matches

        // then the car is not available for the reservation dates
        RestAssured.given()
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .get(availability)
                .then()
                .statusCode(200)
//              needs preview mode for String templates
//                .body(STR."findAll { car -> car.getId() == \{ car.getId()} }", hasSize(0));
               .body("findAll { car -> car.getId() == " + car.getId()+ " }", hasSize(0));
    }
}
