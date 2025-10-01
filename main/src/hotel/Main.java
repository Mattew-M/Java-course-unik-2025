package hotel;

import hotel.model.*;
import hotel.repository.*;
import hotel.util.Logger;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Guest g1 = new Guest("Anna", "Smith", "anna@example.com");
        Guest g2 = new Guest("Bob", "Brown", "bob@example.com");
        Guest g3 = new Guest("Vlad", "Doe", "vlad@example.com");

        GuestRepository guestRepo = new GuestRepository();
        guestRepo.add(g1);
        guestRepo.add(g2);
        guestRepo.add(g3);

        System.out.println("All guests:");
        guestRepo.getAll().forEach(System.out::println);

        System.out.println("\nGuests with last name 'Smith':");
        guestRepo.findByLastName("Smith").forEach(System.out::println);

        System.out.println("\nGuests with email domain 'example.com':");
        guestRepo.findByEmailDomain("example.com").forEach(System.out::println);

        Room r1 = new Room(101, RoomType.STANDARD, 2, 100);
        Room r2 = new Room(102, RoomType.DELUXE, 3, 150);
        Room r3 = new Room(103, RoomType.SUITE, 4, 250);

        RoomRepository roomRepo = new RoomRepository();
        roomRepo.add(r1);
        roomRepo.add(r2);
        roomRepo.add(r3);

        System.out.println("\nRooms with price above 120:");
        roomRepo.findByPriceRange(120, 300).forEach(System.out::println);

        Service s1 = new Service(ServiceType.BREAKFAST);
        Service s2 = new Service(ServiceType.SPA);
        Service s3 = new Service(ServiceType.PARKING);

        ServiceRepository serviceRepo = new ServiceRepository();
        serviceRepo.add(s1);
        serviceRepo.add(s2);
        serviceRepo.add(s3);

        System.out.println("\nServices with price above 20:");
        serviceRepo.findByPriceAbove(20).forEach(System.out::println);

        Reservation res1 = new Reservation(g1, r1, LocalDate.of(2025, 9, 20), LocalDate.of(2025, 9, 22));
        Reservation res2 = new Reservation(g2, r2, LocalDate.of(2025, 9, 21), LocalDate.of(2025, 9, 25));
        Reservation res3 = new Reservation(g3, r3, LocalDate.of(2025, 9, 19), LocalDate.of(2025, 9, 23));

        ReservationRepository resRepo = new ReservationRepository();
        resRepo.add(res1);
        resRepo.add(res2);
        resRepo.add(res3);

        System.out.println("\nReservations for guest 'Vlad Doe':");
        resRepo.findByGuestEmail("vlad@example.com").forEach(System.out::println);

        System.out.println("\nTotal reservations for room 101: " +
                resRepo.countReservationsForRoom(101));

        Invoice inv1 = new Invoice(res1, List.of(s1, s2));
        Invoice inv2 = new Invoice(res2, List.of(s2));
        Invoice inv3 = new Invoice(res3, List.of(s1, s3));

        InvoiceRepository invRepo = new InvoiceRepository();
        invRepo.add(inv1);
        invRepo.add(inv2);
        invRepo.add(inv3);

        System.out.println("\nInvoices for guest with last name 'Smith':");
        invRepo.invoicesForGuest("Smith").forEach(System.out::println);

        System.out.println("\nTotal revenue: " + invRepo.totalRevenue());

        // --- Демонстрація parallelStream ---
        long start = System.nanoTime();
        double totalParallel = invRepo.getAll().parallelStream()
                .mapToDouble(Invoice::totalAmount)
                .sum();
        long duration = System.nanoTime() - start;
        System.out.printf("\nTotal revenue (parallel stream): %.2f, took %.2f ms%n",
                totalParallel, duration / 1e6);

        Logger.info("\nDemo finished.");

        System.out.println("\nAll services from all invoices:");
        List<Service> allServices = invRepo.getAll().stream()
                .flatMap(inv -> inv.services().stream())
                .toList();
        allServices.forEach(System.out::println);

        System.out.println("\nUnique service types across all invoices:");
        invRepo.getAll().stream()
                .flatMap(inv -> inv.services().stream())
                .map(Service::type)
                .distinct()  // залишаємо унікальні
                .forEach(System.out::println);

        double totalServiceCost = invRepo.getAll().stream()
                .flatMap(inv -> inv.services().stream())
                .map(Service::price)
                .reduce(0.0, Double::sum);
        System.out.printf("\nTotal cost of all services: %.2f%n", totalServiceCost);
    }
}
