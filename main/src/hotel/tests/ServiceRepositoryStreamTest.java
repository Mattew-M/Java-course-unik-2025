package hotel.tests;

import hotel.model.*;
import hotel.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceRepositoryStreamTest {

    private ServiceRepository repo;
    private Service s1 = new Service(ServiceType.BREAKFAST);
    private Service s2 = new Service(ServiceType.SPA);
    private Service s3 = new Service(ServiceType.PARKING);

    @BeforeEach
    void setUp() {
        repo = new ServiceRepository();
        repo.add(s1);
        repo.add(s2);
        repo.add(s3);
    }

    @Test
    void testFindByPriceAbove() {
        List<Service> results = repo.findByPriceAbove(20);
        assertEquals(1, results.stream().filter(s -> s.price() > 20).count());
        assertTrue(results.contains(s2));
    }
}
