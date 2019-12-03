package utils;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
    public static Stream<String> readResources(String filename) {
        try {
            URI uri = FileUtils.class.getClassLoader().getResource(filename).toURI();
            return Files.readAllLines(Paths.get(uri)).stream();
        } catch (Exception e) {
            System.err.println("Error reading file " + filename);
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
