package hotel.model;

import hotel.util.Utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Reservation(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
    public Reservation {
        if (!Utils.validateDateRange(startDate, endDate)) {
            throw new IllegalArgumentException("Invalid date range");
        }
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
