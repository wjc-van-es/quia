package nl.vea.reservation.rental;

import java.time.LocalDate;

public class Rental {
    private final Long id;
    private final String userId;
    private final Long reservationId;
    private final LocalDate startDate;

    public Rental(Long id, String userId, Long reservationId,
                  LocalDate startDate) {
        this.id = id;
        this.userId = userId;
        this.reservationId = reservationId;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "\n\tid=" + id +
                ",\n\tuserId='" + userId + "'" +
                ",\n\treservationId=" + reservationId +
                ",\n\tstartDate=" + startDate +
                "\n}";
    }
}
