package nl.vea.reservation.reservation;

import java.time.LocalDate;

public class Reservation {

    private Long id;
    private Long carId;
    private LocalDate startDay;
    private LocalDate endDay;

    /**
     * Check if the given duration overlaps with this reservation
     * @return true if the dates overlap with the reservation, false
     * otherwise
     */
    public boolean isReserved(LocalDate startDay, LocalDate endDay) {
        return (!(this.endDay.isBefore(startDay) ||
            this.startDay.isAfter(endDay)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "\n\tid=" + id +
                ",\n\tcarId=" + carId +
                ",\n\tstartDay=" + startDay +
                ",\n\tendDay=" + endDay +
                "\n}";
    }
}
