package helpers;

import geometry.Point3D;
import utils.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsteroidField {

    private final List<Asteroid3D> asteroids;

    public AsteroidField(Stream<String> coordinates) {
        asteroids = coordinates
                .map(AsteroidField::parseCoordinates)
                .map(Asteroid3D::new)
                .collect(Collectors.toList());
    }

    public void timestep() {
        asteroids.forEach(asteroid -> asteroid.applyGravity(asteroids));
        asteroids.forEach(Asteroid3D::applyVelocity);
    }

    public void timesteps(int N) {
        for (int i = 0; i < N; i++) {
            timestep();
        }
    }

    private static final Pattern COORDINATES_FORMAT = Pattern.compile("<x=\\s*([-+]?\\d+), y=\\s*([-+]?\\d+), z=\\s*([-+]?\\d+)>");

    private static Point3D parseCoordinates(String input) {
        int[] split = StringUtils.getGroups(COORDINATES_FORMAT, input)
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Point3D(split[0], split[1], split[2]);
    }

    public List<Asteroid3D> getAsteroids() {
        return asteroids;
    }
}
