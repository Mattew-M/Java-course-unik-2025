package hotel;

import hotel.loader.GuestLoader;
import hotel.loader.RoomLoader;
import hotel.loader.ServiceLoader;
import hotel.model.*;
import hotel.util.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logger.info("Program started.");

        List<Guest> guests = null;
        List<Room> rooms = null;
        List<Service> services = null;

        try {
            Logger.info("Loading guests from file...");
            guests = GuestLoader.loadFromFile("main/resources/guests.csv");

            Logger.info("Loading rooms from file...");
            rooms = RoomLoader.loadFromFile("main/resources/rooms.csv");

            Logger.info("Loading services from file...");
            services = ServiceLoader.loadFromFile("main/resources/services.csv");

            Logger.info("All data loaded successfully.");

            if (!guests.isEmpty() && !rooms.isEmpty() && services.size() >= 2) {
                Guest guest = guests.get(0);
                Room room = rooms.get(0);
                Service service1 = services.get(0);
                Service service2 = services.get(1);

                Logger.info("Creating reservation...");
                Reservation reservation = new Reservation(
                        guest,
                        room,
                        LocalDate.of(2025, 9, 20),
                        LocalDate.of(2025, 9, 25)
                );
                Logger.info("Reservation created: " + reservation);

                Logger.info("Creating invoice...");
                Invoice invoice = new Invoice(reservation, List.of(service1, service2));
                Logger.info("Invoice created: " + invoice);

                System.out.println("\nDemo reservation and invoice:");
                System.out.println(reservation);
                System.out.println(invoice);
            } else {
                Logger.error("Not enough data to create demo reservation and invoice.");
            }

        } catch (IOException e) {
            Logger.error("File operation error: " + e.getMessage());
        } catch (Exception e) {
            Logger.error("Unexpected error: " + e.getMessage());
        } finally {
            Logger.info("Program finished.");
        }
    }
}
