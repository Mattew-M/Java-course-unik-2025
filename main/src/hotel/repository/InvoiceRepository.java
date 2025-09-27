package hotel.repository;

import hotel.model.Invoice;

public class InvoiceRepository extends GenericRepository<Invoice> {

    public InvoiceRepository() {
        super(inv -> inv.issueDate().toString());
    }

    public void sortByTotalAmount(boolean ascending) {
        sortByComparator(Invoice.BY_TOTAL_AMOUNT, ascending);
    }

    public void sortByGuestLastName(boolean ascending) {
        sortByComparator(Invoice.BY_GUEST_LASTNAME, ascending);
    }
}
