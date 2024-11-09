package nl.vea.inventory.service;

import jakarta.inject.Inject;
import nl.vea.inventory.database.CarInventory;
import nl.vea.inventory.model.Car;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class GraphQLInventoryService {

    @Inject
    CarInventory inventory;

    @Query
    public List<Car> cars(){
        return inventory.getCars();
    }

    @Mutation
    public Car register(Car car) {
        return inventory.addCar(car);
    }

    @Mutation
    public boolean remove(String licensePlateNumber) {
        return inventory.remove(licensePlateNumber);
    }
}
