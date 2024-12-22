package nl.vea.reservation.inventory;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@ApplicationScoped
@GraphQLClientApi(configKey = "inventory")
public interface GraphQLInventoryClient extends InventoryClient {

    @Query("cars")
    Uni<List<Car>> allCars();
}
