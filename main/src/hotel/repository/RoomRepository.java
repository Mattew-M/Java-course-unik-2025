package hotel.repository;

import hotel.model.Room;
import hotel.model.RoomType;
import hotel.util.Logger;

import java.util.List;

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

    public List<Room> findByType(RoomType type) {
        Logger.info("Searching rooms by type: " + type);
        return getAll().stream()
                .filter(r -> r.type() == type)
                .toList();
    }

    public List<Room> findByPriceRange(double min, double max) {
        Logger.info("Searching rooms by price range: " + min + " - " + max);
        return getAll().stream()
                .filter(r -> r.price() >= min && r.price() <= max)
                .toList();
    }

}
