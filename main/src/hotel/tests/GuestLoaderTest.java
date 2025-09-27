package hotel.tests;

import hotel.loader.GuestLoader;
import hotel.model.Guest;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GuestLoaderTest {

    @Test
    void testLoadValidGuests() throws IOException {
        Path tempFile = Files.createTempFile("guests", ".txt");
        Files.writeString(tempFile, "John,Doe,john.doe@example.com\nJane,Smith,jane.smith@example.com");

        List<Guest> guests = GuestLoader.loadFromFile(tempFile.toString());

        assertEquals(2, guests.size());
        assertEquals("John", guests.get(0).firstName());
        assertEquals("Smith", guests.get(1).lastName());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testSkipInvalidLines() throws IOException {
        Path tempFile = Files.createTempFile("guests", ".txt");
        Files.writeString(tempFile, "John,Doe,john.doe@example.com\nInvalidLineWithoutCommas");

        List<Guest> guests = GuestLoader.loadFromFile(tempFile.toString());

        assertEquals(1, guests.size());
        assertEquals("John", guests.get(0).firstName());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testEmptyFileReturnsEmptyList() throws IOException {
        Path tempFile = Files.createTempFile("guests", ".txt");

        List<Guest> guests = GuestLoader.loadFromFile(tempFile.toString());

        assertTrue(guests.isEmpty());
        Files.deleteIfExists(tempFile);
    }
}
