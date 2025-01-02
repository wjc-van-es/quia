package nl.vea.quia.billing.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDate;

public class Invoice extends PanacheMongoEntity {

    private double totalPrice;
    private boolean paid;
    private Reservation reservation;

    // Json deserialization requires a default constructor
    public Invoice(){}

    public Invoice(double totalPrice, boolean paid, Reservation reservation) {
        this.totalPrice = totalPrice;
        this.paid = paid;
        this.reservation = reservation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Invoice{" +
            "totalPrice=" + totalPrice +
            ", paid=" + paid +
            ", reservation=" + reservation +
            ", id=" + id +
            '}';
    }

    public static final class Reservation {
        private Long id;
        private String userId;
        private Long carId;
        private LocalDate startDay;
        private LocalDate endDay;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
                "id=" + id +
                ",\n\tuserId='" + userId + '\'' +
                ",\n\tcarId=" + carId +
                ",\n\tstartDay=" + startDay +
                ",\n\tendDay=" + endDay +
                    "\n}";
        }
    }
}
