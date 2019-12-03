package utils;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileUtils {
    public static Stream<String> readLines(String filename) {
        try {
            URI uri = FileUtils.class.getClassLoader().getResource(filename).toURI();
            return Files.readAllLines(Paths.get(uri)).stream();
        } catch (Exception e) {
            System.err.println("Error reading file " + filename);
            e.printStackTrace();
            return Stream.empty();
        }
    }

    public static Stream<String> readCommaSeparatedValues(String filename) {
        try {
            URI uri = FileUtils.class.getClassLoader().getResource(filename).toURI();
            List<String> allLines = Files.readAllLines(Paths.get(uri));
            if (allLines.size() > 1) {
                System.err.println("Warning: inputfile contained multiple lines");
            }
            return Pattern.compile(",").splitAsStream(allLines.get(0));
        } catch (Exception e) {
            System.err.println("Error reading file " + filename);
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
