package test;

import hotel.model.Guest;
import hotel.model.Invoice;
import hotel.model.Reservation;
import hotel.model.Room;
import hotel.model.RoomType;
import hotel.model.Service;
import hotel.model.ServiceType;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    @Test
    void testInvoiceTotalCalculation() {
        Guest guest = new Guest("Vlad", "Doe", "vlad.doe@example.com");
        Room room = new Room(101, RoomType.DELUXE, 2, 150.0);
        Reservation reservation = new Reservation(guest, room,
                LocalDate.of(2025, 9, 20), LocalDate.of(2025, 9, 25));

        Service breakfast = new Service(ServiceType.BREAKFAST);
        Service spa = new Service(ServiceType.SPA);

        Invoice invoice = new Invoice(reservation, List.of(breakfast, spa));
        double expectedTotal = reservation.getNights() * room.price() + breakfast.price() + spa.price();

        assertEquals(expectedTotal, invoice.totalAmount());
    }
}
