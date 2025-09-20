package hotel.model;

public enum RoomType {
    STANDARD,
    DELUXE,
    SUITE;

    public double basePrice() {
        return switch (this) {
            case STANDARD -> 100.0;
            case DELUXE -> 150.0;
            case SUITE -> 250.0;
        };
    }
}
