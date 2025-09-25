package test;

import hotel.loader.ServiceLoader;
import hotel.model.Service;
import hotel.model.ServiceType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLoaderTest {

    @Test
    void testLoadValidServices() throws IOException {
        Path tempFile = Files.createTempFile("services", ".txt");
        Files.writeString(tempFile, "BREAKFAST\nSPA");

        List<Service> services = ServiceLoader.loadFromFile(tempFile.toString());

        assertEquals(2, services.size());
        assertEquals(ServiceType.BREAKFAST, services.get(0).type());
        assertEquals(ServiceType.SPA, services.get(1).type());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testSkipInvalidLines() throws IOException {
        Path tempFile = Files.createTempFile("services", ".txt");
        Files.writeString(tempFile, "BREAKFAST\nINVALIDSERVICE");

        List<Service> services = ServiceLoader.loadFromFile(tempFile.toString());

        assertEquals(1, services.size());
        assertEquals(ServiceType.BREAKFAST, services.get(0).type());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testEmptyFileReturnsEmptyList() throws IOException {
        Path tempFile = Files.createTempFile("services", ".txt");

        List<Service> services = ServiceLoader.loadFromFile(tempFile.toString());

        assertTrue(services.isEmpty());
        Files.deleteIfExists(tempFile);
    }
}
