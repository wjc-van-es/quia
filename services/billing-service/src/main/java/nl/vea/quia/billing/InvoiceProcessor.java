package nl.vea.quia.billing;

import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

import nl.vea.quia.billing.data.ReservationInvoice;
import nl.vea.quia.billing.model.Invoice;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class InvoiceProcessor {

    @Incoming("invoices")
    @Outgoing("invoices-requests")
    public Message<Invoice> processInvoice(Message<JsonObject> message){
        ReservationInvoice invoiceMessage = message.getPayload().mapTo(ReservationInvoice.class);
        Invoice invoice = new Invoice(invoiceMessage.getPrice(), false, invoiceMessage.getReservation());

        invoice.persist();
        Log.infof("Processing the invoice:\n%s", invoice);

        return message.withPayload(invoice);
    }
}
