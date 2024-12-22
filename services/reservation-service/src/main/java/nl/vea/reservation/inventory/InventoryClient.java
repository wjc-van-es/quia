package nl.vea.reservation.inventory;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface InventoryClient {

    Uni<List<Car>> allCars();
}
