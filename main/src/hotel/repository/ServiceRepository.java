package hotel.repository;

import hotel.model.Service;
import hotel.util.Logger;

import java.util.List;

public class ServiceRepository extends GenericRepository<Service> {

    public ServiceRepository() {
        super(s -> s.type().name());
    }

    public void sortByPrice(boolean ascending) {
        sortByComparator(Service.BY_PRICE, ascending);
    }

    public List<Service> findByPriceAbove(double price) {
        Logger.info("Searching services with price above: " + price);
        return getAll().stream()
                .filter(s -> s.price() > price)
                .toList();
    }
}
