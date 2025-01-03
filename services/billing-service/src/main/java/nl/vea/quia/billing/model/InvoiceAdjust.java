package nl.vea.quia.billing.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDate;

public class InvoiceAdjust extends PanacheMongoEntity {

    private String rentalId;
    private String userId;
    private LocalDate actualEndDate;
    private double price;
    private  boolean paid;

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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "InvoiceAdjust{" +
                "\n\tid='" + id + '\'' +
                ",\n\trentalId='" + rentalId + '\'' +
                ",\n\tuserId='" + userId + '\'' +
                ",\n\tactualEndDate=" + actualEndDate +
                ",\n\t price=" + price +
                "\n}";
    }
}
