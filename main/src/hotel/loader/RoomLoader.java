package hotel.loader;

import hotel.model.Room;
import hotel.model.RoomType;
import hotel.exception.InvalidDataException;
import hotel.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomLoader {
    public static List<Room> loadFromFile(String path) throws IOException {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                try {
                    if (parts.length != 4) throw new InvalidDataException("Invalid room format at line " + lineNumber);
                    int number = Integer.parseInt(parts[0].trim());
                    RoomType type = RoomType.valueOf(parts[1].trim().toUpperCase());
                    int capacity = Integer.parseInt(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    Room room = new Room(number, type, capacity, price);
                    rooms.add(room);
                    Logger.info("Loaded room: " + room);
                } catch (Exception e) {
                    Logger.error("Line " + lineNumber + " skipped: " + e.getMessage());
                }
            }
        }
        return rooms;
    }
}
