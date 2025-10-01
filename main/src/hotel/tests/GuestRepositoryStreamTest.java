package hotel.tests;

import hotel.model.*;
import hotel.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GuestRepositoryStreamTest {
    private GuestRepository guestRepo;

    @BeforeEach
    void setUp() {
        guestRepo = new GuestRepository();
        guestRepo.add(new Guest("Anna", "Smith", "anna@example.com"));
        guestRepo.add(new Guest("Bob", "Brown", "bob@gmail.com"));
        guestRepo.add(new Guest("Vlad", "Doe", "vlad@example.com"));
    }

    @Test
    void testFindByLastName() {
        List<Guest> smiths = guestRepo.findByLastName("Smith");
        assertEquals(1, smiths.size());
        assertEquals("Anna", smiths.get(0).firstName());
    }

    @Test
    void testFindByEmailDomain() {
        List<Guest> exampleDomain = guestRepo.findByEmailDomain("example.com");
        assertEquals(2, exampleDomain.size());
        assertTrue(exampleDomain.stream().anyMatch(g -> g.email().equals("anna@example.com")));
        assertTrue(exampleDomain.stream().anyMatch(g -> g.email().equals("vlad@example.com")));
    }
}
