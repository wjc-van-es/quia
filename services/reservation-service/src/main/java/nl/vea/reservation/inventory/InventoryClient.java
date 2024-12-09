package nl.vea.reservation.inventory;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface InventoryClient {

    List<Car> allCars();
}
