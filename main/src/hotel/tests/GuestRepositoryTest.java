package hotel.tests;

import hotel.model.Guest;
import hotel.repository.GenericRepository;
import hotel.repository.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GuestRepositoryTest {

    @Test
    void testAddAndFind() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g = new Guest("Test", "User", "test@example.com");
        repo.add(g);

        assertTrue(repo.findByIdentity("test@example.com").isPresent());
        assertEquals(g, repo.findByIdentity("test@example.com").get());
    }

    @Test
    void testRemove() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g = new Guest("Test", "User", "test@example.com");
        repo.add(g);

        assertTrue(repo.remove(g));
        assertFalse(repo.findByIdentity("test@example.com").isPresent());
    }

    @Test
    void testDuplicateAdd() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g = new Guest("Test", "User", "test@example.com");
        repo.add(g);
        repo.add(g);

        assertEquals(2, repo.getAll().size());
    }

    private GuestRepository repo;

    @BeforeEach
    void setUp() {
        repo = new GuestRepository();
        repo.add(new Guest("Anna", "Smith", "anna@example.com"));
        repo.add(new Guest("Bob", "Brown", "bob@example.com"));
        repo.add(new Guest("Vlad", "Doe", "vlad@example.com"));
    }

    @Test
    void testSortByEmailAscDesc() {
        repo.sortByIdentity("asc");
        List<Guest> guests = repo.getAll();
        assertEquals("anna@example.com", guests.get(0).email());
        assertEquals("bob@example.com", guests.get(1).email());
        assertEquals("vlad@example.com", guests.get(2).email());

        repo.sortByIdentity("desc");
        guests = repo.getAll();
        assertEquals("vlad@example.com", guests.get(0).email());
        assertEquals("bob@example.com", guests.get(1).email());
        assertEquals("anna@example.com", guests.get(2).email());
    }

    @Test
    void testSortByLastName() {
        repo.sortByLastName(true);
        List<Guest> guests = repo.getAll();
        assertEquals("Brown", guests.get(0).lastName());
        assertEquals("Doe", guests.get(1).lastName());
        assertEquals("Smith", guests.get(2).lastName());

        repo.sortByLastName(false);
        guests = repo.getAll();
        assertEquals("Smith", guests.get(0).lastName());
        assertEquals("Doe", guests.get(1).lastName());
        assertEquals("Brown", guests.get(2).lastName());
    }

    @Test
    void testSortByFirstName() {
        repo.sortByFirstName(true);
        List<Guest> guests = repo.getAll();
        assertEquals("Anna", guests.get(0).firstName());
        assertEquals("Bob", guests.get(1).firstName());
        assertEquals("Vlad", guests.get(2).firstName());
    }
}
