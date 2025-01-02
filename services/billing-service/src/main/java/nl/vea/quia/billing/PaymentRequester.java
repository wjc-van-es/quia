package nl.vea.quia.billing;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;

import nl.vea.quia.billing.data.InvoiceConfirmation;
import nl.vea.quia.billing.model.Invoice;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.util.Random;

@ApplicationScoped
public class PaymentRequester {

    private final Random random = new Random();

    @Incoming("invoices-requests")
    @Outgoing("invoices-confirmations")
    @Blocking
    public InvoiceConfirmation requestPayment(Invoice invoice) {
        payment(invoice.getReservation().getUserId(), invoice.getTotalPrice(), invoice);

        invoice.setPaid(true);
        invoice.update();
        Log.infof("Invoice %s is paid.", invoice);

        return new InvoiceConfirmation(invoice, true);
    }

    private void payment(String user, double price, Object data) {
        Log.infof("Request for payment user: %s, price: %f, data: %s",
            user, price, data);
        try {
            Thread.sleep(random.nextInt(1000, 5000));
        } catch (InterruptedException e) {
            Log.error("Sleep interrupted.", e);
        }
    }

//    /* uncomment in order to consume confirmation here
    @Incoming("invoices-confirmations")
    public void consume(InvoiceConfirmation invoiceConfirmation) {
        System.out.println(invoiceConfirmation);
    }
//    */
}
