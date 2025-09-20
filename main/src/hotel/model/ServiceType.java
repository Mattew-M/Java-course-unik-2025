package hotel.model;

public enum ServiceType {
    BREAKFAST,
    SPA,
    PARKING;

    public double price() {
        return switch (this) {
            case BREAKFAST -> 15.0;
            case SPA -> 50.0;
            case PARKING -> 10.0;
        };
    }
}
