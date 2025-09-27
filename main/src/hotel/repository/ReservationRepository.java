package hotel.repository;

import hotel.model.Reservation;

public class ReservationRepository extends GenericRepository<Reservation> {

    public ReservationRepository() {
        super(r -> r.startDate().toString());
    }

    public void sortByEndDate(boolean ascending) {
        sortByComparator(Reservation.BY_END_DATE, ascending);
    }

    public void sortByNights(boolean ascending) {
        sortByComparator(Reservation.BY_NIGHTS, ascending);
    }

    public void sortByGuestLastName(boolean ascending) {
        sortByComparator(Reservation.BY_GUEST_LASTNAME, ascending);
    }
}
