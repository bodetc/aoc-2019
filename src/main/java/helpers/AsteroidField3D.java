package helpers;

import geometry.Point3D;
import utils.StringUtils;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsteroidField3D extends AsteroidField<Point3D, Asteroid3D> {

    public AsteroidField3D(Stream<String> coordinates) {
        super(coordinates
                .map(AsteroidField3D::parseCoordinates)
                .map(Asteroid3D::new)
                .collect(Collectors.toList())
        );
    }

    public int energy() {
        return streamAsteroids()
                .mapToInt(Asteroid3D::energy)
                .sum();
    }

    private static final Pattern COORDINATES_FORMAT = Pattern.compile("<x=\\s*([-+]?\\d+), y=\\s*([-+]?\\d+), z=\\s*([-+]?\\d+)>");

    public static Point3D parseCoordinates(String input) {
        int[] split = StringUtils.getGroups(COORDINATES_FORMAT, input)
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Point3D(split[0], split[1], split[2]);
    }
}
