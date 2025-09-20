package hotel.model;

import hotel.util.Utils;

public record Guest(String firstName, String lastName, String email) {
    public Guest {
        if (!Utils.validateEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public static Guest createAnonymousGuest() {
        return new Guest("Anonymous", "Guest", "anon@example.com");
    }
}
