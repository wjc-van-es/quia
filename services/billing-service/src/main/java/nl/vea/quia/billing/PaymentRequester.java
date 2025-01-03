package nl.vea.quia.billing;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;

import nl.vea.quia.billing.data.InvoiceConfirmation;
import nl.vea.quia.billing.model.Invoice;
import nl.vea.quia.billing.model.InvoiceAdjust;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
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

    @Incoming("invoices-adjust")
    @Blocking // Because payment sleeps it needs to run in the worker thread instead of the non-blocking eventloop thread
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void requestAdjustment(InvoiceAdjust invoiceAdjust){
        Log.infof("Invoice adjustment received: %s", invoiceAdjust);
        payment(invoiceAdjust.getUserId(), invoiceAdjust.getPrice(), invoiceAdjust);
        invoiceAdjust.setPaid(true);
        invoiceAdjust.persist();
        Log.infof("Invoice adjustment %s is paid.", invoiceAdjust);
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

    /* uncomment in order to consume confirmation here
    @Incoming("invoices-confirmations")
    public void consume(InvoiceConfirmation invoiceConfirmation) {
        System.out.println(invoiceConfirmation);
    }
//    */
}
