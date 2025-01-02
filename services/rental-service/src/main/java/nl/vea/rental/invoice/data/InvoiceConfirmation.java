package nl.vea.rental.invoice.data;

import java.time.LocalDate;

public class InvoiceConfirmation {

    private Invoice invoice;
    private boolean paid;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "InvoiceConfirmation{" +
            "\n\tinvoice=" + invoice +
            ",\n\tpaid=" + paid +
                "\n}";
    }

    public static final class Invoice {

        private double totalPrice;
        private boolean paid;
        private InvoiceReservation reservation;

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

        public InvoiceReservation getReservation() {
            return reservation;
        }

        public void setReservation(InvoiceReservation reservation) {
            this.reservation = reservation;
        }

        @Override
        public String toString() {
            return "Invoice{" +
                    "totalPrice=" + totalPrice +
                    ", paid=" + paid +
                    ", reservation=" + reservation +
                    '}';
        }
    }
    public static final class InvoiceReservation {
        private Long id;
        private String userId;
        private LocalDate startDay;

        public Long getId() {
            return id;
        }

        public String getUserId() {
            return userId;
        }

        public LocalDate getStartDay() {
            return startDay;
        }

        @Override
        public String toString() {
            return "InvoiceReservation{" +
                    "\n\tid=" + id +
                    ",\n\tuserId='" + userId + '\'' +
                    ",\n\tstartDay=" + startDay +
                    "\n}";
        }
    }
}
