package helpers;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import geometry.Point;
import utils.MathUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class AsteroidLaser {
    private final List<Point> asteroids;

    public AsteroidLaser(Stream<String> input) {
        asteroids = ImmutableList.copyOf(parseInput(input, '#'));
    }

    private boolean isViewBlocked(Point a, Point b) {
        return blockingAsteroids(a, b) > 0;
    }

    private long blockingAsteroids(Point a, Point b) {
        return asteroids.stream()
                .filter(not(a::equals))
                .filter(not(b::equals))
                .filter(c -> c.isCollinear(a, b))
                .filter(c -> c.isBetween(a, b))
                .count();
    }

    public long visibleAsteroids(Point a) {
        return asteroids.stream()
                .filter(not(a::equals))
                .filter(not(b -> isViewBlocked(a, b)))
                .count();
    }

    public Point bestAsteroid() {
        return asteroids.stream()
                .max(Comparator.comparingLong(this::visibleAsteroids))
                .orElseThrow();
    }

    @VisibleForTesting
    double laserAngle(Point origin, Point other) {
        Point a = new Point(0, -1);
        Point b = new Point(other.x - origin.x, other.y - origin.y);
        double theta = Math.atan2( a.x*b.y - a.y*b.x, a.x*b.x + a.y*b.y );
        return MathUtils.correctAngle(theta) + 2. * Math.PI * blockingAsteroids(origin, other);
    }

    public List<Point> laserOrder() {
        return laserOrder(bestAsteroid());
    }

    @VisibleForTesting
    List<Point> laserOrder(Point origin) {
        return asteroids.stream()
                .filter(Predicate.not(origin::equals))
                .sorted(Comparator.comparingDouble(other -> laserAngle(origin, other)))
                .collect(Collectors.toList());
    }

    @VisibleForTesting
    static Point findX(Stream<String> input) {
        return parseInput(input, 'X').get(0);
    }

    private static List<Point> parseInput(Stream<String> input, char match) {
        List<Point> asteroids = new ArrayList<>();
        List<String> lines = input.collect(Collectors.toList());
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == match) {
                    asteroids.add(new Point(x, y));
                }
            }
        }
        return asteroids;
    }
}
