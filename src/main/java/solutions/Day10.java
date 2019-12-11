package solutions;

import geometry.Point;
import helpers.AsteroidField;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Stream;

public class Day10 {
    public static void main(String[] args) {
        Stream<String> input = FileUtils.readLines("day10.txt");
        AsteroidField field = new AsteroidField(input);
        Point bestAsteroid = field.bestAsteroid();
        System.out.println("Best point: " + bestAsteroid);
        long visibleAsteroids = field.visibleAsteroids(bestAsteroid);
        System.out.println("Answer for first star: " + visibleAsteroids);

        List<Point> points = field.laserOrder();
        Point asteroid = points.get(199);
        System.out.println("200th Asteroid: " + asteroid);
        int answer = asteroid.x * 100 + asteroid.y;
        System.out.println("Answer for second star: " + answer);
    }
}
