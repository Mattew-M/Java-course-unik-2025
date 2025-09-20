package hotel;

import hotel.model.*;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            // Використання enum + record
            Room room1 = new Room(101, RoomType.DELUXE, 2, Room.basePrice(RoomType.DELUXE));
            Room room2 = Room.createStandardRoom(102);

            Guest guest = new Guest("Vlad", "Doe", "vlad.doe@example.com");

            Reservation res = new Reservation(
                    guest,
                    room1,
                    LocalDate.of(2025, 9, 20),
                    LocalDate.of(2025, 9, 25)
            );

            Service breakfast = new Service(ServiceType.BREAKFAST);
            Service spa = new Service(ServiceType.SPA);

            Invoice invoice = new Invoice(res, Arrays.asList(breakfast, spa));

            System.out.println(room1);
            System.out.println(guest);
            System.out.println(res);
            System.out.println(invoice);


            RoomType type = RoomType.DELUXE;
            System.out.println("Base price for " + type + " = " + type.basePrice());

            ServiceType service = ServiceType.SPA;
            System.out.println("Price for " + service + " = " + service.price());


            // Перевірка валідації
            Guest badGuest = new Guest("Bad", "User", "wrongEmail");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
