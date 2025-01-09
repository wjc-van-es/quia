package nl.vea.inventory.health;

import io.smallrye.health.api.Wellness;
import jakarta.inject.Inject;
import nl.vea.inventory.repository.CarRepository;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Wellness
public class CarCountCheck implements HealthCheck {

    @Inject
    CarRepository repository;

    @Override
    public HealthCheckResponse call() {
        long carsCount = repository.findAll().count();
        boolean status = carsCount > 0;
        return HealthCheckResponse.builder()
                .name("car-count-check")
                .status(status)
                .withData("cars-count", carsCount)
                .build();
    }
}
