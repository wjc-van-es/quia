package nl.vea.quia.billing.data;


import nl.vea.quia.billing.model.Invoice;

public class InvoiceConfirmation {

    private Invoice invoice;
    private boolean paid;

    public InvoiceConfirmation(Invoice invoice, boolean paid) {
        this.invoice = invoice;
        this.paid = paid;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "InvoiceConfirmation{" +
            "\n\tinvoice=" + invoice +
            ",\n\tpaid=" + paid +
                "\n}";
    }
}
