package hotel.tests;

import hotel.model.Service;
import hotel.model.ServiceType;
import hotel.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceRepositoryTest {

    private ServiceRepository repo;

    @BeforeEach
    void setUp() {
        repo = new ServiceRepository();
        repo.add(new Service(ServiceType.BREAKFAST));
        repo.add(new Service(ServiceType.SPA));
        repo.add(new Service(ServiceType.PARKING));
    }

    @Test
    void testSortByPrice() {
        repo.sortByPrice(true);
        List<Service> list = repo.getAll();
        assertEquals(ServiceType.PARKING, list.get(0).type());
        assertEquals(ServiceType.BREAKFAST, list.get(1).type());
        assertEquals(ServiceType.SPA, list.get(2).type());
    }
}
