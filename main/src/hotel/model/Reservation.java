package hotel.model;

import hotel.util.Utils;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    protected Guest guest;  // protected (для доступу в Invoice)
    protected Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        if (!Utils.validateDateRange(startDate, endDate)) {
            throw new IllegalArgumentException("Invalid date range");
        }
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    public long getNights() {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }

    @Override
    public String toString() {
        return String.format("Reservation: %s in %s from %s to %s", guest, room, Utils.formatDate(startDate), Utils.formatDate(endDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return guest.equals(that.guest) && room.equals(that.room) && startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guest, room, startDate);
    }
}
