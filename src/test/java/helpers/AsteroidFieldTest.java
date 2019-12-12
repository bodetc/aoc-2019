package helpers;

import geometry.Point3D;
import org.junit.jupiter.api.Test;
import utils.FileUtils;
import utils.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsteroidFieldTest {

    private static final Pattern STEPS_FORMAT = Pattern.compile("After ([-+]?\\d+) steps:");
    private static final Pattern ASTEROID_FORMAT = Pattern.compile("pos=<x=\\s*([-+]?\\d+), y=\\s*([-+]?\\d+), z=\\s*([-+]?\\d+)>, vel=<x=\\s*([-+]?\\d+), y=\\s*([-+]?\\d+), z=\\s*([-+]?\\d+)>");

    private static int getStep(String line) {
        List<String> groups = StringUtils.getGroups(STEPS_FORMAT, line);
        return Integer.parseInt(groups.get(0));
    }

    private static Asteroid3D getAsteroid(String line) {
        int[] input = StringUtils.getGroups(ASTEROID_FORMAT, line)
                .stream()
                .mapToInt(Integer::parseInt)
                .toArray();

        Point3D position = new Point3D(input[0], input[1], input[2]);
        Point3D velocity = new Point3D(input[3], input[4], input[5]);
        return new Asteroid3D(position, velocity);
    }

    @Test
    void testTimesteps() {
        List<String> result = FileUtils.readLines("helpers/asteroid-field/test-results.txt")
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i += 5) {
            int step = getStep(result.get(i));
            AsteroidField field = new AsteroidField(FileUtils.readLines("helpers/asteroid-field/test-input.txt"));
            field.timesteps(step);

            Asteroid3D asteroid1 = getAsteroid(result.get(i + 1));
            Asteroid3D asteroid2 = getAsteroid(result.get(i + 2));
            Asteroid3D asteroid3 = getAsteroid(result.get(i + 3));
            Asteroid3D asteroid4 = getAsteroid(result.get(i + 4));

            List<Asteroid3D> actual = field.getAsteroids();

            assertEquals(asteroid1, actual.get(0));
            assertEquals(asteroid2, actual.get(1));
            assertEquals(asteroid3, actual.get(2));
            assertEquals(asteroid4, actual.get(3));
        }
    }

    @Test
    void testEnergy() {
        AsteroidField field = new AsteroidField(FileUtils.readLines("helpers/asteroid-field/test-input.txt"));
        field.timesteps(10);
        assertEquals(179, field.energy());
    }

    @Test
    void testTimesteps2() {
        List<String> result = FileUtils.readLines("helpers/asteroid-field/test-results2.txt")
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i += 5) {
            int step = getStep(result.get(i));
            AsteroidField field = new AsteroidField(FileUtils.readLines("helpers/asteroid-field/test-input2.txt"));
            field.timesteps(step);

            Asteroid3D asteroid1 = getAsteroid(result.get(i + 1));
            Asteroid3D asteroid2 = getAsteroid(result.get(i + 2));
            Asteroid3D asteroid3 = getAsteroid(result.get(i + 3));
            Asteroid3D asteroid4 = getAsteroid(result.get(i + 4));

            List<Asteroid3D> actual = field.getAsteroids();

            assertEquals(asteroid1, actual.get(0));
            assertEquals(asteroid2, actual.get(1));
            assertEquals(asteroid3, actual.get(2));
            assertEquals(asteroid4, actual.get(3));
        }
    }

    @Test
    void testEnergy2() {
        AsteroidField field = new AsteroidField(FileUtils.readLines("helpers/asteroid-field/test-input2.txt"));
        field.timesteps(100);
        assertEquals(1940, field.energy());
    }

    @Test
    void testFindCycleTime() {
        long cycleTime = AsteroidField.findCycleTime(FileUtils.readLines("helpers/asteroid-field/test-input.txt"));
        assertEquals(2772, cycleTime);
    }
}