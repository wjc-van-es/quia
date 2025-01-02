package nl.vea.rental.invoice;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import nl.vea.rental.entities.Rental;
import nl.vea.rental.invoice.data.InvoiceConfirmation;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class InvoiceConfirmationService {

    @Incoming("invoices-confirmations")
    public void invoicePaid(InvoiceConfirmation invoiceConfirmation){
        Log.info("Received invoice confirmation " + invoiceConfirmation);

        if (!invoiceConfirmation.isPaid()) {
            Log.warn("Received unpaid invoice confirmation - "
                    + invoiceConfirmation);
            // retry handling omitted
        }

        InvoiceConfirmation.InvoiceReservation reservation =
                invoiceConfirmation.getInvoice().getReservation();

        Rental.findByUserAndReservationIdsOptional(reservation.getUserId(), reservation.getId())
                .ifPresentOrElse(rental -> {
                    rental.setPaid(true);
                    rental.update();
                }, () -> {
                    Rental rental = new Rental(reservation.getUserId(), reservation.getId(), reservation.getStartDay());
                    rental.setActive(false);
                    rental.setPaid(true);
                    rental.persist();
                });

    }
}
