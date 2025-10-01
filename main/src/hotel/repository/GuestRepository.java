package hotel.repository;

import hotel.model.Guest;
import hotel.util.Logger;

import java.util.List;

public class GuestRepository extends GenericRepository<Guest> {

    public GuestRepository() {
        super(Guest::email);
    }

    public void sortByLastName(boolean ascending) {
        sortByComparator(Guest.BY_LASTNAME, ascending);
    }

    public void sortByFirstName(boolean ascending) {
        sortByComparator(Guest.BY_FIRSTNAME, ascending);
    }

    public List<Guest> findByLastName(String lastName) {
        Logger.info("Searching guests by last name: " + lastName);
        return getAll().stream()
                .filter(g -> g.lastName().equalsIgnoreCase(lastName))
                .toList();
    }

    public List<Guest> findByEmailDomain(String domain) {
        Logger.info("Searching guests by email domain: " + domain);
        return getAll().stream()
                .filter(g -> g.email().endsWith("@" + domain))
                .toList();
    }
}
