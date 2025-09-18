package hotel.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Utils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : "N/A";
    }

    public static boolean validateEmail(String email) {
        return ValidationHelper.isValidEmail(email);
    }

    public static boolean validatePositive(double value) {
        return ValidationHelper.isPositiveNumber(value);
    }

    public static boolean validateDateRange(LocalDate start, LocalDate end) {
        return ValidationHelper.isDateRangeValid(start, end);
    }
}
