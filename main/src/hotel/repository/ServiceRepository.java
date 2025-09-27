package hotel.repository;

import hotel.model.Service;

public class ServiceRepository extends GenericRepository<Service> {

    public ServiceRepository() {
        super(s -> s.type().name());
    }

    public void sortByPrice(boolean ascending) {
        sortByComparator(Service.BY_PRICE, ascending);
    }
}
