package nl.vea.quia.users.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {

    private Long id;
    private Long carId;
    private LocalDate startDay;
    private LocalDate endDay;
    private String userId = "anonymous";

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (Objects.nonNull(userId)) {
            this.userId = userId;
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Reservation that)) return false;

        return id.equals(that.id) && carId.equals(that.carId) && userId.equals(that.userId)
                && startDay.equals(that.startDay) && endDay.equals(that.endDay);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + carId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + startDay.hashCode();
        result = 31 * result + endDay.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "\n\tid=" + id +
                ",\n\tcarId=" + carId +
                ",\n\tuserId=" + userId +
                ",\n\tstartDay=" + startDay +
                ",\n\tendDay=" + endDay +
                "\n}";
    }
}
