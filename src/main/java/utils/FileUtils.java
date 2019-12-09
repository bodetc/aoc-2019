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

    private static Stream<String> readCommaSeparatedValues(String filename) {
        Stream<String> allLines = readLines(filename);
        return COMMA_SEPARATOR.splitAsStream(allLines.findFirst().orElseThrow());
    }

    public static int[] readCommaSeparatedInts(String filename) {
        return readCommaSeparatedValues(filename)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static long[] readCommaSeparatedLongs(String filename) {
        return readCommaSeparatedValues(filename)
                .mapToLong(Integer::parseInt)
                .toArray();
    }

    public static int[] readInts(String filename) {
        Stream<String> allLines = readLines(filename);
        return allLines.findFirst().orElseThrow().chars()
                .mapToObj(i -> (char) i)
                .map(c -> Character.toString(c))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
