package hotel.repository;

import hotel.model.Reservation;
import hotel.util.Logger;

import java.util.List;

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

    public List<Reservation> findByGuestEmail(String email) {
        Logger.info("Searching reservations by guest email: " + email);
        return getAll().stream()
                .filter(r -> r.guest().email().equalsIgnoreCase(email))
                .toList();
    }

    public long countReservationsForRoom(int roomNumber) {
        Logger.info("Counting reservations for room: " + roomNumber);
        return getAll().stream()
                .filter(r -> r.room().roomNumber() == roomNumber)
                .count();
    }
}
