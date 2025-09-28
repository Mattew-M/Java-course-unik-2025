package hotel.tests;

import hotel.model.*;
import hotel.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest {

    private ReservationRepository repo;
    private Guest g1 = new Guest("Anna", "Smith", "anna@example.com");
    private Guest g2 = new Guest("Bob", "Brown", "bob@example.com");
    private Guest g3 = new Guest("Vlad", "Doe", "vlad@example.com");
    private Room r = new Room(101, RoomType.STANDARD, 2, 100);

    @BeforeEach
    void setUp() {
        repo = new ReservationRepository();
        repo.add(new Reservation(g1, r, LocalDate.of(2025,9,20), LocalDate.of(2025,9,22)));
        repo.add(new Reservation(g2, r, LocalDate.of(2025,9,21), LocalDate.of(2025,9,24)));
        repo.add(new Reservation(g3, r, LocalDate.of(2025,9,19), LocalDate.of(2025,9,23)));
    }

    @Test
    void testSortByStartDate() {
        repo.sortByIdentity("asc");
        List<Reservation> list = repo.getAll();
        assertEquals(g3, list.get(0).guest());
        assertEquals(g1, list.get(1).guest());
        assertEquals(g2, list.get(2).guest());
    }

    @Test
    void testSortByEndDate() {
        repo.sortByEndDate(true);
        List<Reservation> list = repo.getAll();
        assertEquals(g1, list.get(0).guest());
        assertEquals(g3, list.get(1).guest());
        assertEquals(g2, list.get(2).guest());
    }

    @Test
    void testSortByNights() {
        repo.sortByNights(false); // descending
        List<Reservation> list = repo.getAll();
        assertEquals(g3, list.get(0).guest()); // 4 ночі
        assertEquals(g2, list.get(1).guest()); // 3 ночі
        assertEquals(g1, list.get(2).guest()); // 2 ночі
    }


    @Test
    void testSortByGuestLastName() {
        repo.sortByGuestLastName(true);
        List<Reservation> list = repo.getAll();
        assertEquals("Brown", list.get(0).guest().lastName());
        assertEquals("Doe", list.get(1).guest().lastName());
        assertEquals("Smith", list.get(2).guest().lastName());
    }
}
