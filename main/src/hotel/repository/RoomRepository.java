package hotel.repository;

import hotel.model.Room;

public class RoomRepository extends GenericRepository<Room> {

    public RoomRepository() {
        super(r -> String.valueOf(r.roomNumber()));
    }

    public void sortByPrice(boolean ascending) {
        sortByComparator(Room.BY_PRICE, ascending);
    }

    public void sortByCapacity(boolean ascending) {
        sortByComparator(Room.BY_CAPACITY, ascending);
    }
}
