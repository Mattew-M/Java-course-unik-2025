package hotel.tests;

import hotel.model.Guest;
import hotel.repository.GenericRepository;
import org.junit.jupiter.api.Test;

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
        repo.add(g); // Дубль

        assertEquals(2, repo.getAll().size());
    }
}
