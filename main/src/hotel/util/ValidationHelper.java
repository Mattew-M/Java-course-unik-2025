package hotel.util;

import java.time.LocalDate;

class ValidationHelper {
    static boolean isPositiveNumber(double value) {
        return value > 0;
    }

    static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    static boolean isDateRangeValid(LocalDate start, LocalDate end) {
        return start != null && end != null && !end.isBefore(start);
    }
}
