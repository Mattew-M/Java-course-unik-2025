package hotel.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import hotel.util.Utils;

public class Invoice {
    private Reservation reservation;
    private List<Service> services;
    private double totalAmount;
    private LocalDate issueDate;

    public Invoice(Reservation reservation, List<Service> services) {
        this.reservation = reservation;
        this.services = services;
        this.issueDate = LocalDate.now();
        calculateTotal();
    }

    private void calculateTotal() {
        totalAmount = reservation.getNights() * reservation.getRoom().getPrice();
        for (Service s : services) {
            totalAmount += s.getPrice();
        }
    }

    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return String.format("Invoice for %s\nRoom: %s\nTotal: %.2f$\nIssued: %s",
                reservation.getGuest(),
                reservation.getRoom(),
                totalAmount,
                Utils.formatDate(issueDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return reservation.equals(invoice.reservation) && issueDate.equals(invoice.issueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation, issueDate);
    }
}
