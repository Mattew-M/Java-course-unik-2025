package hotel.repository;

import hotel.model.Guest;

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
}
