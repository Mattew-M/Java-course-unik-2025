package hotel.model;

import hotel.util.Utils;

public record Room(int roomNumber, RoomType type, int capacity, double price) {
    public Room {
        if (!Utils.validatePositive(price) || capacity <= 0) {
            throw new IllegalArgumentException("Invalid room data");
        }
    }

    public static Room createStandardRoom(int number) {
        return new Room(number, RoomType.STANDARD, 2, basePrice(RoomType.STANDARD));
    }

    public static double basePrice(RoomType type) {
        return switch (type) {
            case STANDARD -> 100.0;
            case DELUXE -> 150.0;
            case SUITE -> 250.0;
        };
    }
}
