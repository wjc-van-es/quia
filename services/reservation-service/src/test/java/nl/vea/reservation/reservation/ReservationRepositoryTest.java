package nl.vea.reservation.reservation;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import nl.vea.reservation.reservation.entities.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ReservationRepositoryTest {

    @Test
    @Transactional
    public void testCreateReservation(){

        // given
        Reservation reservation = new Reservation();
        reservation.setStartDay(LocalDate.now().plus(5, ChronoUnit.DAYS));
        reservation.setEndDay(LocalDate.now().plus(12, ChronoUnit.DAYS));
        reservation.setCarId(384L);

        // when
        Reservation.persist(reservation);

        // then
        assertNotNull(reservation.getId());
        Optional<Reservation> result = Reservation.findByIdOptional(reservation.getId());
        assertTrue(result.isPresent());
        assertEquals(reservation, result.get());
    }
}
