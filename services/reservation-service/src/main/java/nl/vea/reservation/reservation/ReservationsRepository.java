package nl.vea.reservation.reservation;

import nl.vea.reservation.reservation.entities.Reservation;

import java.util.List;

public interface ReservationsRepository {

    List<Reservation> findAll();

    Reservation save(Reservation reservation);
}
