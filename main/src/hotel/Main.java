package hotel;

import hotel.model.*;
import hotel.repository.*;
import hotel.util.Logger;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // === Створення об'єктів Guest ===
        Guest g1 = new Guest("Anna", "Smith", "anna@example.com");
        Guest g2 = new Guest("Bob", "Brown", "bob@example.com");
        Guest g3 = new Guest("Vlad", "Doe", "vlad@example.com");

        GuestRepository guestRepo = new GuestRepository();
        guestRepo.add(g1);
        guestRepo.add(g2);
        guestRepo.add(g3);

        System.out.println("Guests before sorting:");
        guestRepo.getAll().forEach(System.out::println);

        guestRepo.sortByLastName(true);
        System.out.println("\nGuests sorted by last name ascending:");
        guestRepo.getAll().forEach(System.out::println);

        guestRepo.sortByIdentity("desc");
        System.out.println("\nGuests sorted by email descending:");
        guestRepo.getAll().forEach(System.out::println);

        Room r1 = new Room(101, RoomType.STANDARD, 2, 100);
        Room r2 = new Room(102, RoomType.DELUXE, 3, 150);
        Room r3 = new Room(103, RoomType.SUITE, 4, 250);

        RoomRepository roomRepo = new RoomRepository();
        roomRepo.add(r1);
        roomRepo.add(r2);
        roomRepo.add(r3);

        System.out.println("\nRooms before sorting:");
        roomRepo.getAll().forEach(System.out::println);

        roomRepo.sortByPrice(true);
        System.out.println("\nRooms sorted by price ascending:");
        roomRepo.getAll().forEach(System.out::println);

        roomRepo.sortByCapacity(false);
        System.out.println("\nRooms sorted by capacity descending:");
        roomRepo.getAll().forEach(System.out::println);

        Service s1 = new Service(ServiceType.BREAKFAST);
        Service s2 = new Service(ServiceType.SPA);
        Service s3 = new Service(ServiceType.PARKING);

        ServiceRepository serviceRepo = new ServiceRepository();
        serviceRepo.add(s1);
        serviceRepo.add(s2);
        serviceRepo.add(s3);

        System.out.println("\nServices before sorting:");
        serviceRepo.getAll().forEach(System.out::println);

        serviceRepo.sortByPrice(false);
        System.out.println("\nServices sorted by price descending:");
        serviceRepo.getAll().forEach(System.out::println);

        Reservation res1 = new Reservation(g1, r1,
                LocalDate.of(2025, 9, 20),
                LocalDate.of(2025, 9, 22));
        Reservation res2 = new Reservation(g2, r2,
                LocalDate.of(2025, 9, 21),
                LocalDate.of(2025, 9, 25));
        Reservation res3 = new Reservation(g3, r3,
                LocalDate.of(2025, 9, 19),
                LocalDate.of(2025, 9, 23));

        ReservationRepository resRepo = new ReservationRepository();
        resRepo.add(res1);
        resRepo.add(res2);
        resRepo.add(res3);

        System.out.println("\nReservations before sorting:");
        resRepo.getAll().forEach(System.out::println);

        resRepo.sortByNights(true);
        System.out.println("\nReservations sorted by number of nights ascending:");
        resRepo.getAll().forEach(System.out::println);

        resRepo.sortByGuestLastName(false);
        System.out.println("\nReservations sorted by guest last name descending:");
        resRepo.getAll().forEach(System.out::println);

        Invoice inv1 = new Invoice(res1, List.of(s1, s2));
        Invoice inv2 = new Invoice(res2, List.of(s2));
        Invoice inv3 = new Invoice(res3, List.of(s1, s3));

        InvoiceRepository invRepo = new InvoiceRepository();
        invRepo.add(inv1);
        invRepo.add(inv2);
        invRepo.add(inv3);

        System.out.println("\nInvoices before sorting:");
        invRepo.getAll().forEach(System.out::println);

        invRepo.sortByTotalAmount(true);
        System.out.println("\nInvoices sorted by total amount ascending:");
        invRepo.getAll().forEach(System.out::println);

        invRepo.sortByGuestLastName(false);
        System.out.println("\nInvoices sorted by guest last name descending:");
        invRepo.getAll().forEach(System.out::println);

        Logger.info("\nDemo finished.");
    }
}
