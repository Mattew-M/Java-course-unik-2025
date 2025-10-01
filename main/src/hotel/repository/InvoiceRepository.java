package hotel.repository;

import hotel.model.Invoice;
import hotel.util.Logger;

import java.util.List;

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

    public double totalRevenue() {
        Logger.info("Calculating total revenue");
        return getAll().stream()
                .mapToDouble(Invoice::totalAmount)
                .sum();
    }

    public List<Invoice> invoicesForGuest(String lastName) {
        Logger.info("Searching invoices for guest last name: " + lastName);
        return getAll().stream()
                .filter(inv -> inv.reservation().guest().lastName().equalsIgnoreCase(lastName))
                .toList();
    }
}
