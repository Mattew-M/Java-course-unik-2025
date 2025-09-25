package test;

import hotel.model.Room;
import hotel.model.RoomType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testValidRoomCreation() {
        Room room = new Room(101, RoomType.DELUXE, 2, 150.0);
        assertEquals(101, room.roomNumber());
        assertEquals(RoomType.DELUXE, room.type());
        assertEquals(2, room.capacity());
        assertEquals(150.0, room.price());
    }

    @Test
    void testNegativeCapacityThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Room(102, RoomType.STANDARD, -1, 100.0)
        );
    }

    @Test
    void testNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Room(103, RoomType.SUITE, 2, -250.0)
        );
    }
}
