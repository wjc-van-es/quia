package nl.vea.quia.users.model;

import java.util.Objects;

public class Car {

    private Long id;
    private String licensePlateNumber;
    private String manufacturer;
    private String model;

    // framework needs a default constructor when it deserializes a Car instance from json
    public Car(){}

    public Car(Long id, String licensePlateNumber,
               String manufacturer, String model) {
        this.id = id;
        this.licensePlateNumber = licensePlateNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(licensePlateNumber, car.licensePlateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licensePlateNumber);
    }

    @Override
    public String toString() {
        return "Car{" +
                "\n\tid=" + id +
                ",\n\tlicensePlateNumber='" + licensePlateNumber + '\'' +
                ",\n\tmanufacturer='" + manufacturer + '\'' +
                "\n\t, model='" + model + '\'' +
                "\n}";
    }
}
