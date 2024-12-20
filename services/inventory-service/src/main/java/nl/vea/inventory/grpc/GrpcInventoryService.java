package nl.vea.inventory.grpc;


import io.quarkus.grpc.GrpcService;
import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import nl.vea.inventory.model.*;
import nl.vea.inventory.repository.CarRepository;

@GrpcService
public class GrpcInventoryService implements InventoryService {

//    @Inject
//    CarInventory inventory;

    @Inject
    CarRepository carRepository;

    @Override
    @Blocking
    @Transactional
    public Uni<CarResponse> addUni(InsertCarRequest request) {
        // addCar will create a new id for the Car
//        Car car = inventory.addCar(new Car(
//                request.getLicensePlateNumber(), request.getManufacturer(), request.getModel()));
        Car car = new Car(
                request.getLicensePlateNumber(), request.getManufacturer(), request.getModel());
        carRepository.persist(car);
        Log.infof("Persisting a new car: %s", car);
        return Uni.createFrom().item(CarResponse.newBuilder()
                .setLicensePlateNumber(car.getLicensePlateNumber())
                .setManufacturer(car.getManufacturer())
                .setModel(car.getModel())
                .setId(car.getId())
                .build());
    }

    @Override
    @Blocking
    public Multi<CarResponse> add(Multi<InsertCarRequest> requests) {
        return requests
                .map(request -> {
                    Car car = new Car(
                            request.getLicensePlateNumber(), request.getManufacturer(), request.getModel());
                    return car;
                })
                .onItem()
                .invoke(car -> {
                    QuarkusTransaction.requiringNew().run( () -> {
                        carRepository.persist(car);
                        Log.infof("Persisting a new car: %s", car);
                    });

                })
                .map(car -> CarResponse.newBuilder()
                        .setLicensePlateNumber(car.getLicensePlateNumber())
                        .setManufacturer(car.getManufacturer())
                        .setModel(car.getModel())
                        .setId(car.getId())
                        .build());
    }

    @Override
    @Blocking
    @Transactional
    public Uni<CarResponse> remove(RemoveCarRequest request) {
        boolean success = carRepository.removeByLicensePlate(request.getLicensePlateNumber());
        //boolean success = inventory.remove(request.getLicensePlateNumber());
        Log.infof("The car(s) with licence plate number %s, was removed: %s",
                request.getLicensePlateNumber(), success);
        if (success) {
            return Uni.createFrom().item(CarResponse.newBuilder()
                    .setLicensePlateNumber(request.getLicensePlateNumber())
                    .build());
        }
        return Uni.createFrom().nullItem();
    }
}
