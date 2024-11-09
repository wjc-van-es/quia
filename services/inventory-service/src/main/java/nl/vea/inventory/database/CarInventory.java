package nl.vea.inventory.database;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import nl.vea.inventory.model.Car;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class CarInventory {
    private List<Car> cars;

    public static final AtomicLong ids = new AtomicLong(0);

    @PostConstruct
    void initialize() {
        cars = new CopyOnWriteArrayList<>();
        initialData();
    }

    public Car addCar(Car car) {
        car.setId(ids.incrementAndGet());
        cars.add(car);
        return car;
    }

    /**
     * Will remove a car based on its licensePlateNumber
     *
     * @param licensePlateNumber should match the car's to be removed
     * @return indicates whether removal took place. Will be false when no matching car was found.
     */
    public boolean remove(String licensePlateNumber) {
        return cars.stream()
                .filter(car -> licensePlateNumber.contentEquals(car.getLicensePlateNumber()))
                .map(cars::remove) // all cars with matching licence plate number are removed should only be one
                .reduce(false, (b1, b2) -> b1 || b2); // should only return true if at least one matching car was removed.
    }

    public List<Car> getCars() {
        return cars;
    }

    private void initialData() {
        cars.add(new Car(ids.incrementAndGet(), "ABC-123", "Mazda", "6"));
        cars.add(new Car(ids.incrementAndGet(), "XYZ-666", "Ford", "Mustang"));
        cars.add(new Car(ids.incrementAndGet(), "ABC-987", "Honda", "Jazz"));
        cars.add(new Car(ids.incrementAndGet(), "XYZ-123", "Renault", "Clio"));
        cars.add(new Car(ids.incrementAndGet(), "XYZ-987", "Ford", "Focus"));
        cars.add(new Car(ids.incrementAndGet(), "ZZZ-000", "Seat", "Ibiza"));
    }
}
