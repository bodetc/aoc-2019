package helpers;

import com.google.common.collect.ImmutableList;
import geometry.Point;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidLaserTest {

    @Test
    void testVisibleAsteroids() {
        Stream<String> input = FileUtils.readLines("helpers/asteroid-laser/test1.txt");
        AsteroidLaser field = new AsteroidLaser(input);
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
        Stream<String> input = FileUtils.readLines("helpers/asteroid-laser/test1.txt");
        AsteroidLaser field = new AsteroidLaser(input);
        assertEquals(new Point(3, 4), field.bestAsteroid());
    }

    @Test
    void testBestAsteroid2() {
        Stream<String> input = FileUtils.readLines("helpers/asteroid-laser/test2.txt");
        AsteroidLaser field = new AsteroidLaser(input);
        Point best = new Point(5, 8);
        assertEquals(best, field.bestAsteroid());
        assertEquals(33, field.visibleAsteroids(best));
    }

    @Test
    void testBestAsteroid3() {
        Stream<String> input = FileUtils.readLines("helpers/asteroid-laser/test3.txt");
        AsteroidLaser field = new AsteroidLaser(input);
        Point best = new Point(1, 2);
        assertEquals(best, field.bestAsteroid());
        assertEquals(35, field.visibleAsteroids(best));
    }

    @Test
    void testBestAsteroid4() {
        Stream<String> input = FileUtils.readLines("helpers/asteroid-laser/test4.txt");
        AsteroidLaser field = new AsteroidLaser(input);
        Point best = new Point(6, 3);
        assertEquals(best, field.bestAsteroid());
        assertEquals(41, field.visibleAsteroids(best));
    }

    @Test
    void testBestAsteroid5() {
        Stream<String> input = FileUtils.readLines("helpers/asteroid-laser/test5.txt");
        AsteroidLaser field = new AsteroidLaser(input);
        Point best = new Point(11, 13);
        assertEquals(best, field.bestAsteroid());
        assertEquals(210, field.visibleAsteroids(best));
    }

    @Test
    void testAngle() {
        AsteroidLaser field = new AsteroidLaser(ImmutableList.<String>of().stream());
        Point origin = new Point(5, 5);
        assertEquals(0., field.laserAngle(origin, new Point(5, 0)));
        assertEquals(Math.PI / 2., field.laserAngle(origin, new Point(10, 5)));
        assertEquals(Math.PI, field.laserAngle(origin, new Point(5, 10)));
        assertEquals(3. * Math.PI / 2., field.laserAngle(origin, new Point(0, 5)));
    }

    @Test
    void testLaser() {
        AsteroidLaser field = new AsteroidLaser(FileUtils.readLines("helpers/asteroid-laser/laser.txt"));
        Point origin = AsteroidLaser.findX(FileUtils.readLines("helpers/asteroid-laser/laser.txt"));
        List<Point> laserOrder = field.laserOrder(origin);
        assertEquals(new Point(8, 1), laserOrder.get(0));
        assertEquals(new Point(9, 0), laserOrder.get(1));
        assertEquals(new Point(9, 1), laserOrder.get(2));
        assertEquals(new Point(10, 0), laserOrder.get(3));
        assertEquals(new Point(9, 2), laserOrder.get(4));
        assertEquals(new Point(11, 1), laserOrder.get(5));
        assertEquals(new Point(12, 1), laserOrder.get(6));
        assertEquals(new Point(11, 2), laserOrder.get(7));
        assertEquals(new Point(15, 1), laserOrder.get(8));
    }

    @Test
    void testLaser5() {
        AsteroidLaser field = new AsteroidLaser(FileUtils.readLines("helpers/asteroid-laser/test5.txt"));
        Point origin = field.bestAsteroid();
        List<Point> laserOrder = field.laserOrder(origin);
        assertEquals(new Point(11, 12), laserOrder.get(0));
        assertEquals(new Point(12, 1), laserOrder.get(1));
        assertEquals(new Point(12, 2), laserOrder.get(2));
        assertEquals(new Point(12, 8), laserOrder.get(9));
        assertEquals(new Point(16, 0), laserOrder.get(19));
        assertEquals(new Point(16, 9), laserOrder.get(49));
        assertEquals(new Point(10, 16), laserOrder.get(99));
        assertEquals(new Point(9, 6), laserOrder.get(198));
        assertEquals(new Point(8, 2), laserOrder.get(199));
        assertEquals(new Point(10, 9), laserOrder.get(200));
        assertEquals(new Point(11, 1), laserOrder.get(298));
    }
}