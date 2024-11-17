package nl.vea.inventory.grpc;


import io.quarkus.grpc.GrpcService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import nl.vea.inventory.database.CarInventory;
import nl.vea.inventory.model.*;
import nl.vea.inventory.model.CarResponse;
import nl.vea.inventory.model.InsertCarRequest;
import nl.vea.inventory.model.InventoryService;
import nl.vea.inventory.model.RemoveCarRequest;

@GrpcService
public class GrpcInventoryService implements InventoryService {

    @Inject
    CarInventory inventory;

    @Override
    public Uni<CarResponse> addUni(InsertCarRequest request) {
        // addCar will create a new id for the Car
        Car car = inventory.addCar(new Car(
                request.getLicensePlateNumber(), request.getManufacturer(), request.getModel()));
        Log.infof("Persisting a new car: %s", car);
        return Uni.createFrom().item(CarResponse.newBuilder()
                .setLicensePlateNumber(car.getLicensePlateNumber())
                .setManufacturer(car.getManufacturer())
                .setModel(car.getModel())
                .setId(car.getId())
                .build());
    }

    @Override
    public Multi<CarResponse> add(Multi<InsertCarRequest> requests) {
        return requests
                .map(request -> inventory.addCar(new Car(
                        request.getLicensePlateNumber(), request.getManufacturer(), request.getModel())))
                .onItem()
                .invoke(car -> Log.infof("Persisting a new car: %s", car))
                .map(car -> CarResponse.newBuilder()
                        .setLicensePlateNumber(car.getLicensePlateNumber())
                        .setManufacturer(car.getManufacturer())
                        .setModel(car.getModel())
                        .setId(car.getId())
                        .build());
    }

    @Override
    public Uni<CarResponse> remove(RemoveCarRequest request) {
        boolean success = inventory.remove(request.getLicensePlateNumber());
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
