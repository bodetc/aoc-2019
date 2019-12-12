package solutions;

import geometry.Point;
import helpers.AsteroidLaser;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {
    @Test
    void regressionTestFirstStar() {
        Stream<String> input = FileUtils.readLines("day10.txt");
        AsteroidLaser field = new AsteroidLaser(input);
        Point best = new Point(8, 16);
        assertEquals(best, field.bestAsteroid());
        assertEquals(214, field.visibleAsteroids(best));
    }

    @Test
    void regressionTestSecondStar() {
        Stream<String> input = FileUtils.readLines("day10.txt");
        AsteroidLaser field = new AsteroidLaser(input);
        Point answer = new Point(5, 2);
        assertEquals(answer, field.laserOrder().get(199));
    }
}