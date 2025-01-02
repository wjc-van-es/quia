package nl.vea.quia.billing.data;

import nl.vea.quia.billing.model.Invoice;

public class ReservationInvoice {
    private Invoice.Reservation reservation;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Invoice.Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Invoice.Reservation reservation) {
        this.reservation = reservation;
    }
}
