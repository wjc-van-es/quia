package nl.vea.rental.billing;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class AdjustedInvoiceMonitor {

    @Incoming("invoices-adjust")
    public void monitor(InvoiceAdjust invoiceAdjust){
        Log.infof("Consuming invoiceAdjust from Kafka topic:\n%s", invoiceAdjust);
    }
}
