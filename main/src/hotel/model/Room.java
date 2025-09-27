package hotel.model;

import hotel.util.Utils;
import java.util.Comparator;

public record Room(int roomNumber, RoomType type, int capacity, double price) implements Comparable<Room> {

    public Room {
        if (!Utils.validatePositive(price) || capacity <= 0) {
            throw new IllegalArgumentException("Invalid room data: " + roomNumber);
        }
    }

    public static Room createStandardRoom(int number) {
        return new Room(number, RoomType.STANDARD, 2, RoomType.STANDARD.basePrice());
    }

    @Override
    public int compareTo(Room other) {
        return Integer.compare(this.roomNumber, other.roomNumber);
    }

    public static final Comparator<Room> BY_PRICE = Comparator.comparingDouble(Room::price);
    public static final Comparator<Room> BY_CAPACITY = Comparator.comparingInt(Room::capacity);
}
