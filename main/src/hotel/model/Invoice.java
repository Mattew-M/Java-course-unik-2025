package hotel.model;

import hotel.util.Utils;
import java.time.LocalDate;
import java.util.List;
import java.util.Comparator;

public record Invoice(Reservation reservation, List<Service> services, double totalAmount, LocalDate issueDate)
        implements Comparable<Invoice> {

    public Invoice(Reservation reservation, List<Service> services) {
        this(reservation, services, calculateTotal(reservation, services), LocalDate.now());
    }

    private static double calculateTotal(Reservation reservation, List<Service> services) {
        double total = reservation.getNights() * reservation.room().price();
        for (Service s : services) {
            total += s.price();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Invoice for %s%nRoom: %s%nTotal: %.2f$%nIssued: %s",
                reservation.guest(),
                reservation.room(),
                totalAmount,
                Utils.formatDate(issueDate));
    }

    // Comparable за датою виписки
    @Override
    public int compareTo(Invoice other) {
        return this.issueDate.compareTo(other.issueDate);
    }

    // Додаткові Comparator-и
    public static final Comparator<Invoice> BY_TOTAL_AMOUNT = Comparator.comparingDouble(Invoice::totalAmount);
    public static final Comparator<Invoice> BY_GUEST_LASTNAME = Comparator.comparing(inv -> inv.reservation().guest().lastName());
}
