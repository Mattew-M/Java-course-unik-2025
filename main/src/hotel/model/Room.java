package hotel.model;

import hotel.util.Utils;

public record Room(int roomNumber, RoomType type, int capacity, double price) {
    public Room {
        if (!Utils.validatePositive(price) || capacity <= 0) {
            throw new IllegalArgumentException("Invalid room data: " + roomNumber);
        }
    }

    public static Room createStandardRoom(int number) {
        return new Room(number, RoomType.STANDARD, 2, RoomType.STANDARD.basePrice());
    }
}
