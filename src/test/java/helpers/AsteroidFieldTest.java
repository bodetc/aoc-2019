package helpers;

import geometry.Point;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidFieldTest {

    @Test
    void testVisibleAsteroids() {
        Stream<String> input = FileUtils.readLines("helpers/asteroidfield/test1.txt");
        AsteroidField field = new AsteroidField(input);
        assertEquals(7, field.visibleAsteroids(new Point(1, 0)));
        assertEquals(7, field.visibleAsteroids(new Point(4, 0)));
        assertEquals(6, field.visibleAsteroids(new Point(0, 2)));
        assertEquals(7, field.visibleAsteroids(new Point(1, 2)));
        assertEquals(7, field.visibleAsteroids(new Point(2, 2)));
        assertEquals(7, field.visibleAsteroids(new Point(3, 2)));
        assertEquals(5, field.visibleAsteroids(new Point(4, 2)));
        assertEquals(7, field.visibleAsteroids(new Point(4, 3)));
        assertEquals(8, field.visibleAsteroids(new Point(3, 4)));
        assertEquals(7, field.visibleAsteroids(new Point(4, 4)));
    }

    @Test
    void testBestAsteroid() {
        Stream<String> input = FileUtils.readLines("helpers/asteroidfield/test1.txt");
        AsteroidField field = new AsteroidField(input);
        assertEquals(new Point(3, 4), field.bestAsteroid());
    }

    @Test
    void testBestAsteroid2() {
        Stream<String> input = FileUtils.readLines("helpers/asteroidfield/test2.txt");
        AsteroidField field = new AsteroidField(input);
        Point best = new Point(5, 8);
        assertEquals(best, field.bestAsteroid());
        assertEquals(33, field.visibleAsteroids(best));
    }

    @Test
    void testBestAsteroid3() {
        Stream<String> input = FileUtils.readLines("helpers/asteroidfield/test3.txt");
        AsteroidField field = new AsteroidField(input);
        Point best = new Point(1, 2);
        assertEquals(best, field.bestAsteroid());
        assertEquals(35, field.visibleAsteroids(best));
    }

    @Test
    void testBestAsteroid4() {
        Stream<String> input = FileUtils.readLines("helpers/asteroidfield/test4.txt");
        AsteroidField field = new AsteroidField(input);
        Point best = new Point(6, 3);
        assertEquals(best, field.bestAsteroid());
        assertEquals(41, field.visibleAsteroids(best));
    }

    @Test
    void testBestAsteroid5() {
        Stream<String> input = FileUtils.readLines("helpers/asteroidfield/test5.txt");
        AsteroidField field = new AsteroidField(input);
        Point best = new Point(11, 13);
        assertEquals(best, field.bestAsteroid());
        assertEquals(210, field.visibleAsteroids(best));
    }
}