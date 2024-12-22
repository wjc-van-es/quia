package nl.vea.reservation.reservation;

import io.quarkus.logging.Log;
import io.quarkus.test.hibernate.reactive.panache.TransactionalUniAsserter;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.vertx.RunOnVertxContext;
import nl.vea.reservation.reservation.entities.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ReservationPersistenceTest {

    @Test
    @RunOnVertxContext
    public void testCreateReservation(TransactionalUniAsserter asserter){

        // given
        Reservation reservation = new Reservation();
        reservation.setStartDay(LocalDate.now().plus(5, ChronoUnit.DAYS));
        reservation.setEndDay(LocalDate.now().plus(12, ChronoUnit.DAYS));
        reservation.setCarId(384L);

        // when
        asserter.<Reservation>assertThat(() -> reservation.persist(),
                persistedReservation -> {
                    assertNotNull(persistedReservation.getId());
                    asserter.putData("persistedReservation", persistedReservation);
                });

        // then
        asserter.assertThat(() -> Reservation.<Reservation>findById(
                ((Reservation)asserter.getData("persistedReservation")).getId()),
                retrievedReservation -> {
                    Log.infof("retrieved Reservation: %s", retrievedReservation);
                    assertNotNull(retrievedReservation);
                    assertEquals(asserter.getData("persistedReservation"), retrievedReservation);
                    assertEquals(384L, retrievedReservation.getCarId());
                });
    }
}
