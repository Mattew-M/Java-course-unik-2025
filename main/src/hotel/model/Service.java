package hotel.model;

import hotel.util.Utils;

public record Service(ServiceType type, double price) {
    public Service(ServiceType type) {
        this(type, switch (type) {
            case BREAKFAST -> 15.0;
            case SPA -> 50.0;
            case PARKING -> 10.0;
        });
    }

    public Service {
        if (!Utils.validatePositive(price)) {
            throw new IllegalArgumentException("Invalid service price");
        }
    }
}
