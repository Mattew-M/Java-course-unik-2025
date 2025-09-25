package test;

import hotel.model.Guest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void testValidGuestCreation() {
        Guest guest = new Guest("John", "Doe", "john.doe@example.com");
        assertEquals("John", guest.firstName());
        assertEquals("Doe", guest.lastName());
        assertEquals("john.doe@example.com", guest.email());
    }

    @Test
    void testInvalidEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Guest("John", "Doe", "invalidEmail")
        );
    }

    @Test
    void testAnonymousGuestCreation() {
        Guest guest = Guest.createAnonymousGuest();
        assertEquals("Anonymous", guest.firstName());
        assertEquals("Guest", guest.lastName());
    }
}
