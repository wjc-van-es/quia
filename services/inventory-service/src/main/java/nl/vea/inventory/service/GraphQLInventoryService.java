package nl.vea.inventory.service;

import io.micrometer.core.annotation.Counted;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nl.vea.inventory.model.Car;
import nl.vea.inventory.repository.CarRepository;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class GraphQLInventoryService {

    @Inject
    CarRepository carRepository;

    @Query
    public List<Car> cars(){
        return carRepository.listAll();
    }

    @WithSpan
    @Counted(description = "Number of car registrations")
    @Transactional
    @Mutation
    public Car register(Car car) {
        carRepository.persist(car);
        Log.infof("Persisted %s", car);
        return car;
    }

    @WithSpan
    @Counted(description = "Number of cars removed")
    @Transactional
    @Mutation
    public boolean remove(String licensePlateNumber) {
        //return inventory.remove(licensePlateNumber);
        return carRepository.removeByLicensePlate(licensePlateNumber);
    }
}
