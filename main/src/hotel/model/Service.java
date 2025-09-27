package hotel.model;

import java.util.Comparator;

public record Service(ServiceType type, double price) implements Comparable<Service> {

    public Service {
        if (price <= 0) throw new IllegalArgumentException("Invalid service price");
    }

    public Service(ServiceType type) {
        this(type, type.price());
    }

    @Override
    public int compareTo(Service other) {
        return this.type.name().compareTo(other.type.name());
    }

    public static final Comparator<Service> BY_PRICE = Comparator.comparingDouble(Service::price);
}
