package nl.vea.reservation.reservation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ReservationRepositoryTest {

    @Inject
    ReservationsRepository repository;

    @Test
    public void testCreateReservation(){

        // given
        Reservation reservation = new Reservation();
        reservation.setStartDay(LocalDate.now().plus(5, ChronoUnit.DAYS));
        reservation.setEndDay(LocalDate.now().plus(12, ChronoUnit.DAYS));
        reservation.setCarId(384L);

        // when
        reservation = repository.save(reservation);

        // then
        assertNotNull(reservation.getId());
        assertTrue(repository.findAll().contains(reservation));
    }
}
