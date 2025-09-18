package hotel.model;

import java.util.Objects;
import hotel.util.Utils;

public class Guest {
    private String firstName;
    private String lastName;
    private String email;

    public Guest(String firstName, String lastName, String email) {
        if (!Utils.validateEmail(email)) throw new IllegalArgumentException("Invalid email");
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static Guest createAnonymousGuest() {
        return new Guest("Anonymous", "Guest", "anon@example.com");
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return email.equals(guest.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
