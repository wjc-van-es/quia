package nl.vea.inventory.client;


import io.quarkus.grpc.GrpcClient;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import nl.vea.inventory.model.InsertCarRequest;
import nl.vea.inventory.model.InventoryService;
import nl.vea.inventory.model.RemoveCarRequest;

@QuarkusMain
public class InventoryCommand implements QuarkusApplication {

    private static final String USAGE =
            "Usage: inventory <add>|<remove> " +
                    "<license plate number> <manufacturer> <model>";

    @GrpcClient("inventory")
    InventoryService inventory;

    public void addUni(String licencePlateNumber, String manufacturer, String model) {
        inventory.addUni(InsertCarRequest.newBuilder()
                .setLicensePlateNumber(licencePlateNumber)
                .setManufacturer(manufacturer)
                .setModel(model)
                .build())
                .onItem()
                .invoke(carResponse ->
                        System.out.printf("Inserted new car: %s\n", carResponse))
                .await().indefinitely();
    }

    public void remove(String licensePlateNumber) {
        inventory.remove(RemoveCarRequest.newBuilder()
                        .setLicensePlateNumber(licensePlateNumber)
                        .build())
                .onItem().invoke(carResponse ->
                        System.out.println("Removed car " + carResponse))
                .await().indefinitely();
    }

    @Override
    public int run(String... args) throws Exception {
        String action =
                args.length > 0 ? args[0] : null;
        if ("add".equals(action) && args.length >= 4) {
            addUni(args[1], args[2], args[3]);
            return 0;
        } else if ("remove".equals(action) && args.length >= 2) {
            remove(args[1]);
            return 0;
        }
        System.err.println(USAGE);
        return 1;
    }
}
