package hotel.model;

import hotel.util.Utils;
import java.util.Comparator;

public record Guest(String firstName, String lastName, String email) implements Comparable<Guest> {

    public Guest {
        if (!Utils.validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }

    public static Guest createAnonymousGuest() {
        return new Guest("Anonymous", "Guest", "anon@example.com");
    }

    @Override
    public int compareTo(Guest other) {
        return this.email.compareTo(other.email);
    }

    public static final Comparator<Guest> BY_LASTNAME = Comparator.comparing(Guest::lastName);
    public static final Comparator<Guest> BY_FIRSTNAME = Comparator.comparing(Guest::firstName);
}
