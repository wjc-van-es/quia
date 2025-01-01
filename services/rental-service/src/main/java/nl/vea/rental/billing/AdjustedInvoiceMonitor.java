package nl.vea.rental.billing;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class AdjustedInvoiceMonitor {

    // Note this doesn't work in combination with nl.vea.rental.rest.RentalResourceTest.testRentalProlongedInvoiceSend
    // apparently, when a message is consumed from the topic by another consumer no next record is available
    // https://smallrye.io/smallrye-reactive-messaging/latest/kafka/test-companion/
    //@Incoming("invoices-adjust")
    public void monitor(InvoiceAdjust invoiceAdjust){
        Log.infof("Consuming invoiceAdjust from Kafka topic:\n%s", invoiceAdjust);
    }
}
