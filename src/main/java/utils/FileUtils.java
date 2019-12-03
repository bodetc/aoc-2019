package utils;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileUtils {

    public static final Pattern COMMA_SEPARATOR = Pattern.compile(",");

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
        Stream<String> allLines = readLines(filename);
        return COMMA_SEPARATOR.splitAsStream(allLines.findFirst().orElseThrow());
    }
}
