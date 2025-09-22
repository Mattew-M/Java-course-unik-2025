package hotel.tests;

import hotel.model.Room;
import hotel.model.RoomType;
import hotel.repository.GenericRepository;
import org.junit.jupiter.api.Test;
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
}