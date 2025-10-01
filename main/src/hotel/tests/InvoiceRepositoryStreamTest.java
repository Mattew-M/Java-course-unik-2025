package hotel.tests;

import hotel.model.*;
import hotel.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceRepositoryStreamTest {

    private InvoiceRepository repo;
    private Guest g1 = new Guest("Anna", "Smith", "anna@example.com");
    private Guest g2 = new Guest("Bob", "Brown", "bob@example.com");
    private Room r = new Room(101, RoomType.STANDARD, 2, 100);
    private Reservation res1 = new Reservation(g1, r, LocalDate.of(2025,9,20), LocalDate.of(2025,9,22));
    private Reservation res2 = new Reservation(g2, r, LocalDate.of(2025,9,21), LocalDate.of(2025,9,25));
    private Service s1 = new Service(ServiceType.BREAKFAST);
    private Service s2 = new Service(ServiceType.SPA);

    @BeforeEach
    void setUp() {
        repo = new InvoiceRepository();
        repo.add(new Invoice(res1, List.of(s1, s2)));
        repo.add(new Invoice(res2, List.of(s2)));
    }

    @Test
    void testInvoicesForGuest() {
        List<Invoice> smithInvoices = repo.invoicesForGuest("Smith");
        assertEquals(1, smithInvoices.size());
        assertEquals("Smith", smithInvoices.get(0).reservation().guest().lastName());
    }

    @Test
    void testTotalRevenue() {
        double expected = repo.getAll().stream().mapToDouble(Invoice::totalAmount).sum();
        assertEquals(expected, repo.totalRevenue());
    }
}
