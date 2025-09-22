package hotel;

import hotel.model.*;
import hotel.repository.GenericRepository;

public class MainGenericsDemo {
    public static void main(String[] args) {
        GenericRepository<Guest> guestRepo = new GenericRepository<>(Guest::email);
        Guest g1 = new Guest("Vlad", "Doe", "vlad.doe@example.com");
        Guest g2 = new Guest("Anna", "Smith", "anna.smith@example.com");
        guestRepo.add(g1);
        guestRepo.add(g2);

        guestRepo.remove(g1);

        guestRepo.findByIdentity("anna.smith@example.com")
                .ifPresent(g -> System.out.println("Found guest: " + g));

        GenericRepository<Room> roomRepo = new GenericRepository<>(r -> String.valueOf(r.roomNumber()));
        Room r1 = new Room(101, RoomType.DELUXE, 2, 150);
        Room r2 = new Room(102, RoomType.STANDARD, 2, 100);
        roomRepo.add(r1);
        roomRepo.add(r2);
        roomRepo.add(r2);

        System.out.println("All rooms:");
        roomRepo.getAll().forEach(System.out::println);
        roomRepo.remove(r2);
        System.out.println("After removal:");
        roomRepo.getAll().forEach(System.out::println);
    }
}