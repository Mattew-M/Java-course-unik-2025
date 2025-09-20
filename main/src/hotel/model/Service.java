package hotel.model;

import hotel.util.Utils;

public record Service(ServiceType type, double price) {
    public Service {
        if (!Utils.validatePositive(price)) {
            throw new IllegalArgumentException("Invalid service price");
        }
    }

    public Service(ServiceType type) {
        this(type, type.price());
    }
}
