package hotel.model;

import java.util.Objects;
import hotel.util.Utils;

public class Service {
    private String name;
    private double price;

    public Service(String name, double price) {
        if (!Utils.validatePositive(price)) throw new IllegalArgumentException("Invalid service price");
        this.name = name;
        this.price = price;
    }

    public static Service breakfast() {
        return new Service("Breakfast", 15.0);
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " - $" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return name.equals(service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
