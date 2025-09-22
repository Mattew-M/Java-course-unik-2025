package hotel.tests;

import hotel.model.Guest;
import hotel.repository.GenericRepository;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

class GuestRepositoryTest {

    @Test
    void testAddAndFind() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g = new Guest("Test", "User", "test@example.com");
        repo.add(g);

        // Перевірка, що гість доданий і його можна знайти
        assertTrue(repo.findByIdentity("test@example.com").isPresent());
        assertEquals(g, repo.findByIdentity("test@example.com").get());
    }

    @Test
    void testRemove() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g = new Guest("Test", "User", "test@example.com");
        repo.add(g);

        // Перевірка видалення
        assertTrue(repo.remove(g));
        assertFalse(repo.findByIdentity("test@example.com").isPresent());
    }

    @Test
    void testDuplicateAdd() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g = new Guest("Test", "User", "test@example.com");
        repo.add(g);
        repo.add(g); // Дубль

        // Перевірка, що обидва об’єкти присутні у списку
        assertEquals(2, repo.getAll().size());
    }
}
