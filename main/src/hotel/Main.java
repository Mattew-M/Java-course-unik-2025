package hotel;

import hotel.loader.GuestLoader;
import hotel.loader.RoomLoader;
import hotel.loader.ServiceLoader;
import hotel.model.*;
import hotel.util.Logger;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Guest> guests = null;
        List<Room> rooms = null;
        List<Service> services = null;

        try {
            guests = GuestLoader.loadFromFile("main/resources/guests.csv");
            rooms = RoomLoader.loadFromFile("main/resources/rooms.csv");
            services = ServiceLoader.loadFromFile("main/resources/services.csv");

            Logger.info("All valid guests from CSV:");
            guests.forEach(g -> System.out.println("  " + g));

            Logger.info("All valid rooms from CSV:");
            rooms.forEach(r -> System.out.println("  " + r));

            Logger.info("All valid services from CSV:");
            services.forEach(s -> System.out.println("  " + s));

            Guest guest = guests.get(0);
            Room room = rooms.get(0);
            Service service1 = services.get(0);
            Service service2 = services.get(1);

            Reservation res = new Reservation(
                    guest,
                    room,
                    java.time.LocalDate.of(2025, 9, 20),
                    java.time.LocalDate.of(2025, 9, 25)
            );

            Invoice invoice = new Invoice(res, List.of(service1, service2));

            System.out.println("\nDemo reservation and invoice:");
            System.out.println(res);
            System.out.println(invoice);

        } catch (IOException e) {
            Logger.error("File operation error: " + e.getMessage());
        } catch (Exception e) {
            Logger.error("Unexpected error: " + e.getMessage());
        } finally {
            Logger.info("Program finished.");
        }
    }
}
