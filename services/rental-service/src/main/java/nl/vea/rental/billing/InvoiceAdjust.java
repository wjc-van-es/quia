package nl.vea.rental.billing;

import java.time.LocalDate;

public class InvoiceAdjust {

    private String rentalId;
    private String userId;
    private LocalDate actualEndDate;
    private double price;

    // empty constructor is necessary for deserialization from JSON
    public InvoiceAdjust() {
    }

    public InvoiceAdjust(String rentalId, String userId, LocalDate actualEndDate, double price) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.actualEndDate = actualEndDate;
        this.price = price;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
