package test;

import hotel.model.Guest;
import hotel.model.Reservation;
import hotel.model.Room;
import hotel.model.RoomType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void testValidReservationAndNights() {
        Guest guest = new Guest("Anna", "Smith", "anna.smith@example.com");
        Room room = new Room(101, RoomType.STANDARD, 2, 100.0);
        LocalDate start = LocalDate.of(2025, 9, 20);
        LocalDate end = LocalDate.of(2025, 9, 25);

        Reservation reservation = new Reservation(guest, room, start, end);
        assertEquals(5, reservation.getNights());
    }

    @Test
    void testInvalidDateRangeThrowsException() {
        Guest guest = new Guest("Anna", "Smith", "anna.smith@example.com");
        Room room = new Room(101, RoomType.STANDARD, 2, 100.0);
        LocalDate start = LocalDate.of(2025, 9, 25);
        LocalDate end = LocalDate.of(2025, 9, 20);

        assertThrows(IllegalArgumentException.class, () ->
                new Reservation(guest, room, start, end)
        );
    }
}
