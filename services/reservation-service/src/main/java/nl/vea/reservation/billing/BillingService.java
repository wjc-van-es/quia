package nl.vea.reservation.billing;

import io.quarkus.logging.Log;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.Random;
import java.util.random.RandomGenerator;

public class BillingService {

    @Incoming("invoices")
    public void processInvoice(Invoice invoice){
 /*       if(Random.from(RandomGenerator.getDefault()).nextBoolean()){
            var message = String.format("We simulate a problem with processing invoice: %s", invoice);
            Log.error(message);
            throw new RuntimeException(message);
        }*/
        Log.infof("Processing the received invoice: %s", invoice);
    }
}
