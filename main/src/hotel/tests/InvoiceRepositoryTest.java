package hotel.tests;

import hotel.model.*;
import hotel.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceRepositoryTest {

    private InvoiceRepository repo;
    private Guest g1 = new Guest("Anna", "Smith", "anna@example.com");
    private Guest g2 = new Guest("Bob", "Brown", "bob@example.com");
    private Room r = new Room(101, RoomType.STANDARD, 2, 100);
    private Reservation res1 = new Reservation(g1, r, LocalDate.of(2025,9,20), LocalDate.of(2025,9,22));
    private Reservation res2 = new Reservation(g2, r, LocalDate.of(2025,9,21), LocalDate.of(2025,9,25));

    @BeforeEach
    void setUp() {
        repo = new InvoiceRepository();
        repo.add(new Invoice(res1, List.of(new Service(ServiceType.BREAKFAST))));
        repo.add(new Invoice(res2, List.of(new Service(ServiceType.SPA))));
    }

    @Test
    void testSortByTotalAmount() {
        repo.sortByTotalAmount(true);
        List<Invoice> list = repo.getAll();
        assertTrue(list.get(0).totalAmount() <= list.get(1).totalAmount());
    }

    @Test
    void testSortByGuestLastName() {
        repo.sortByGuestLastName(true);
        List<Invoice> list = repo.getAll();
        assertEquals("Brown", list.get(0).reservation().guest().lastName());
        assertEquals("Smith", list.get(1).reservation().guest().lastName());
    }
}
