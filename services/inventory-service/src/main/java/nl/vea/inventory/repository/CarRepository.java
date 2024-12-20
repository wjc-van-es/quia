package nl.vea.inventory.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import nl.vea.inventory.model.Car;

import java.util.Optional;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

//    public Optional<Car> findByLicensePlateNumberOptional(String licencePlateNumber){
//        return find("licencePlateNumber", licencePlateNumber).firstResultOptional();
//    }

    @Transactional
    public boolean removeByLicensePlate(String licensePlateNumber){
        return find("licensePlateNumber", licensePlateNumber)
                .stream()
                .peek(car -> Log.infof("candidate to be deleted: %s", car))
                .map(car -> this.deleteById(car.getId())) // we use this method, because it returns a boolean
                .peek(deleted -> Log.infof("Was candidate successfully deleted? %s", deleted))
                // if the stream contained any car & any of these was successfully deleted it will return true
                // even if multiple cars with the same licencePlateNumber were found & deleted
                .reduce(false, (b1, b2) -> b1 || b2);
    }
}
