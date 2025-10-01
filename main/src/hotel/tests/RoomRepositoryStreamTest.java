package hotel.tests;

import hotel.model.*;
import hotel.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomRepositoryStreamTest {

    private RoomRepository repo;
    private Room r1 = new Room(101, RoomType.STANDARD, 2, 100);
    private Room r2 = new Room(102, RoomType.DELUXE, 3, 150);
    private Room r3 = new Room(103, RoomType.SUITE, 4, 250);

    @BeforeEach
    void setUp() {
        repo = new RoomRepository();
        repo.add(r1);
        repo.add(r2);
        repo.add(r3);
    }

    @Test
    void testFindByType() {
        List<Room> deluxeRooms = repo.findByType(RoomType.DELUXE);
        assertEquals(1, deluxeRooms.size());
        assertEquals(102, deluxeRooms.get(0).roomNumber());
    }

    @Test
    void testFindByPriceRange() {
        List<Room> roomsInRange = repo.findByPriceRange(120, 260);
        assertEquals(2, roomsInRange.size());
        assertTrue(roomsInRange.stream().allMatch(r -> r.price() >= 120 && r.price() <= 260));
    }
}
