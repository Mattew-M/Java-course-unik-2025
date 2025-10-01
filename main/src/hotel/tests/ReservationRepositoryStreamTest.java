package hotel.tests;

import hotel.model.*;
import hotel.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryStreamTest {

    private ReservationRepository repo;
    private Guest g1 = new Guest("Anna", "Smith", "anna@example.com");
    private Guest g2 = new Guest("Bob", "Brown", "bob@example.com");
    private Room r1 = new Room(101, RoomType.STANDARD, 2, 100);
    private Room r2 = new Room(102, RoomType.DELUXE, 3, 150);
    private Reservation res1 = new Reservation(g1, r1, LocalDate.of(2025,9,20), LocalDate.of(2025,9,22));
    private Reservation res2 = new Reservation(g2, r2, LocalDate.of(2025,9,21), LocalDate.of(2025,9,25));

    @BeforeEach
    void setUp() {
        repo = new ReservationRepository();
        repo.add(res1);
        repo.add(res2);
    }

    @Test
    void testFindByGuestEmail() {
        List<Reservation> results = repo.findByGuestEmail("anna@example.com");
        assertEquals(1, results.size());
        assertEquals("Anna", results.get(0).guest().firstName());
    }

    @Test
    void testCountReservationsForRoom() {
        long count = repo.countReservationsForRoom(101);
        assertEquals(1, count);
    }
}
