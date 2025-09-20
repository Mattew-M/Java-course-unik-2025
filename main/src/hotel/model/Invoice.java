package hotel.model;

import hotel.util.Utils;

import java.time.LocalDate;
import java.util.List;

public record Invoice(Reservation reservation, List<Service> services, double totalAmount, LocalDate issueDate) {
    public Invoice(Reservation reservation, List<Service> services) {
        this(reservation, services,
                calculateTotal(reservation, services),
                LocalDate.now());
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
}
