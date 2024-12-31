package nl.vea.rental.reservation;

import java.time.LocalDate;

public class Reservation {
    private Long id;
    private Long carId;
    private LocalDate startDay;
    private LocalDate endDay;
    private String userId = "anonymous";

    public Long getId() {
        return id;
    }

    public Long getCarId() {
        return carId;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "\nid=" + id +
                ",\n\tcarId=" + carId +
                ",\n\tstartDay=" + startDay +
                ",\n\tendDay=" + endDay +
                ",\n\tuserId='" + userId + '\'' +
                "\n}";
    }
}
