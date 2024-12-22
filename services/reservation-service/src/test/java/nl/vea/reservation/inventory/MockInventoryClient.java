package nl.vea.reservation.inventory;

import io.quarkus.test.Mock;
import io.smallrye.mutiny.Uni;

import java.util.List;

@Mock
public class MockInventoryClient implements GraphQLInventoryClient{

    @Override
    public Uni<List<Car>> allCars() {
        Car peugeot = new Car(1L, "ABC123", "Peugeot", "406");
        return Uni.createFrom().item(List.of(peugeot));
    }
}
