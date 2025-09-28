package hotel.model;

import hotel.util.Utils;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public record Reservation(Guest guest, Room room, LocalDate startDate, LocalDate endDate)
        implements Comparable<Reservation> {

    public Reservation {
        if (!Utils.validateDateRange(startDate, endDate)) {
            throw new IllegalArgumentException("Invalid date range");
        }
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    @Override
    public int compareTo(Reservation other) {
        return this.startDate.compareTo(other.startDate);
    }

    public static final Comparator<Reservation> BY_END_DATE = Comparator.comparing(Reservation::endDate);
    public static final Comparator<Reservation> BY_NIGHTS = Comparator.comparingLong(Reservation::getNights);
    public static final Comparator<Reservation> BY_GUEST_LASTNAME = Comparator.comparing(r -> r.guest().lastName());
}
