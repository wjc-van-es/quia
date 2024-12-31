package nl.vea.reservation.billing;

import nl.vea.reservation.reservation.entities.Reservation;

public class Invoice {
    private final Reservation reservation;
    private final double price;

    public Invoice(Reservation reservation, double price) {
        this.reservation = reservation;
        this.price = price;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "\n\treservation=" + reservation +
                ",\n\tprice=" + price +
                "\n}";
    }
}
