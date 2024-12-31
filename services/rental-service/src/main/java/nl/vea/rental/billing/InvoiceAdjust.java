package nl.vea.rental.billing;

import java.time.LocalDate;

public class InvoiceAdjust {

    private final String rentalId;
    private final String userId;
    private final LocalDate actualEndDate;
    private final double price;

    public InvoiceAdjust(String rentalId, String userId, LocalDate actualEndDate, double price) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.actualEndDate = actualEndDate;
        this.price = price;
    }

    @Override
    public String toString() {
        return "InvoiceAdjust{" +
                "\n\trentalId='" + rentalId + '\'' +
                ",\n\tuserId='" + userId + '\'' +
                ",\n\tactualEndDate=" + actualEndDate +
                ",\n\t price=" + price +
                "\n}";
    }
}
