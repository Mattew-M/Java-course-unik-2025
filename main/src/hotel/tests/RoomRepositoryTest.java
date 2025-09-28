package hotel.tests;

import hotel.model.Room;
import hotel.model.RoomType;
import hotel.repository.GenericRepository;
import hotel.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomRepositoryTest {

    @Test
    void testAddAndFindRoom() {
        GenericRepository<Room> repo = new GenericRepository<>(r -> String.valueOf(r.roomNumber()));
        Room r1 = new Room(101, RoomType.DELUXE, 2, 150);
        repo.add(r1);

        assertTrue(repo.findByIdentity("101").isPresent());
        assertEquals(r1, repo.findByIdentity("101").get());
    }

    @Test
    void testRemoveRoom() {
        GenericRepository<Room> repo = new GenericRepository<>(r -> String.valueOf(r.roomNumber()));
        Room r1 = new Room(101, RoomType.DELUXE, 2, 150);
        repo.add(r1);
        assertTrue(repo.remove(r1));
        assertFalse(repo.findByIdentity("101").isPresent());
    }

    private RoomRepository repo;

    @BeforeEach
    void setUp() {
        repo = new RoomRepository();
        repo.add(new Room(101, RoomType.STANDARD, 2, 100));
        repo.add(new Room(102, RoomType.DELUXE, 3, 150));
        repo.add(new Room(103, RoomType.SUITE, 4, 250));
    }

    @Test
    void testSortByRoomNumber() {
        repo.sortByIdentity("desc");
        List<Room> rooms = repo.getAll();
        assertEquals(103, rooms.get(0).roomNumber());
        assertEquals(102, rooms.get(1).roomNumber());
        assertEquals(101, rooms.get(2).roomNumber());
    }

    @Test
    void testSortByPrice() {
        repo.sortByPrice(true);
        List<Room> rooms = repo.getAll();
        assertEquals(100, rooms.get(0).price());
        assertEquals(150, rooms.get(1).price());
        assertEquals(250, rooms.get(2).price());

        repo.sortByPrice(false);
        rooms = repo.getAll();
        assertEquals(250, rooms.get(0).price());
    }

    @Test
    void testSortByCapacity() {
        repo.sortByCapacity(false);
        List<Room> rooms = repo.getAll();
        assertEquals(4, rooms.get(0).capacity());
        assertEquals(3, rooms.get(1).capacity());
        assertEquals(2, rooms.get(2).capacity());
    }
}