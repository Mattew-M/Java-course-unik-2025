package hotel.loader;

import hotel.model.Guest;
import hotel.exception.InvalidDataException;
import hotel.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GuestLoader {
    public static List<Guest> loadFromFile(String path) throws IOException {
        List<Guest> guests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                try {
                    if (parts.length != 3) throw new InvalidDataException("Invalid guest format at line " + lineNumber);
                    Guest guest = new Guest(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    guests.add(guest);
                    Logger.info("Loaded guest: " + guest);
                } catch (Exception e) {
                    Logger.error("Line " + lineNumber + " skipped: " + e.getMessage());
                }
            }
        }
        return guests;
    }
}
