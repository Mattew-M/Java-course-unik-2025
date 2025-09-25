package test;

import hotel.loader.RoomLoader;
import hotel.model.Room;
import hotel.model.RoomType;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomLoaderTest {

    @Test
    void testLoadValidRooms() throws IOException {
        Path tempFile = Files.createTempFile("rooms", ".txt");
        Files.writeString(tempFile, "101,DELUXE,2,150.0\n102,STANDARD,1,100.0");

        List<Room> rooms = RoomLoader.loadFromFile(tempFile.toString());

        assertEquals(2, rooms.size());
        assertEquals(101, rooms.get(0).roomNumber());
        assertEquals(RoomType.STANDARD, rooms.get(1).type());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testSkipInvalidLines() throws IOException {
        Path tempFile = Files.createTempFile("rooms", ".txt");
        Files.writeString(tempFile, "101,DELUXE,2,150.0\nInvalidLineWithoutCommas");

        List<Room> rooms = RoomLoader.loadFromFile(tempFile.toString());

        assertEquals(1, rooms.size());
        assertEquals(101, rooms.get(0).roomNumber());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testEmptyFileReturnsEmptyList() throws IOException {
        Path tempFile = Files.createTempFile("rooms", ".txt");

        List<Room> rooms = RoomLoader.loadFromFile(tempFile.toString());

        assertTrue(rooms.isEmpty());
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testInvalidDataSkipped() throws IOException {
        Path tempFile = Files.createTempFile("rooms", ".txt");
        // некоректний тип кімнати та від'ємна ціна
        Files.writeString(tempFile, "101,DELUXE,2,150.0\n102,WRONGTYPE,2,100.0\n103,STANDARD,2,-50");

        List<Room> rooms = RoomLoader.loadFromFile(tempFile.toString());

        assertEquals(1, rooms.size());
        assertEquals(101, rooms.get(0).roomNumber());

        Files.deleteIfExists(tempFile);
    }
}
