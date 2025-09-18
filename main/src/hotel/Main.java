package hotel;

import hotel.model.*;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Room room1 = new Room(101, "Deluxe", 2, 150);
            Room room2 = Room.createStandardRoom(102);

            Guest guest = new Guest("vlad", "Doe", "vlad.doe@example.com");

            Reservation res = new Reservation(
                    guest,
                    room1,
                    LocalDate.of(2025, 9, 20),
                    LocalDate.of(2025, 9, 25)
            );

            Service breakfast = Service.breakfast();
            Service spa = new Service("Spa", 50);

            Invoice invoice = new Invoice(res, Arrays.asList(breakfast, spa));

            System.out.println(room1);
            System.out.println(guest);
            System.out.println(res);
            System.out.println(invoice);

            Guest badGuest = new Guest("Bad", "User", "wrongEmail");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
