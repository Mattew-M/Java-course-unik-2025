package hotel.model;

import java.util.Objects;
import hotel.util.Utils;

public class Room {
    private int roomNumber;
    private String type;
    private int capacity;
    private double price;

    public Room(int roomNumber, String type, int capacity, double price) {
        if (!Utils.validatePositive(price) || capacity <= 0) {
            throw new IllegalArgumentException("Invalid room data");
        }
        this.roomNumber = roomNumber;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
    }

    public static Room createStandardRoom(int number) {
        return new Room(number, "Standard", 2, 100.0);
    }

    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public int getCapacity() { return capacity; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (!Utils.validatePositive(price)) throw new IllegalArgumentException("Price must be positive");
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Room #%d (%s) - Capacity: %d, Price: %.2f$", roomNumber, type, capacity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
