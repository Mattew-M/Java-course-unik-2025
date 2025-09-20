package hotel.loader;

import hotel.model.Service;
import hotel.model.ServiceType;
import hotel.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceLoader {
    public static List<Service> loadFromFile(String path) throws IOException {
        List<Service> services = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;
                try {
                    ServiceType type = ServiceType.valueOf(line.trim().toUpperCase());
                    Service service = new Service(type);
                    services.add(service);
                    Logger.info("Loaded service: " + service);
                } catch (Exception e) {
                    Logger.error("Line " + lineNumber + " skipped: " + e.getMessage());
                }
            }
        }
        return services;
    }
}
