package helpers;

import com.google.common.collect.ImmutableList;
import geometry.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class AsteroidField {
    private final List<Point> asteroids;

    public AsteroidField(Stream<String> input) {
        asteroids = ImmutableList.copyOf(parseInput(input));
    }

    private boolean isViewBlocked(Point a, Point b) {
        return asteroids.stream()
                .filter(not(a::equals))
                .filter(not(b::equals))
                .filter(c -> c.isCollinear(a, b))
                .anyMatch(c -> c.isBetween(a, b));
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

    private static List<Point> parseInput(Stream<String> input) {
        List<Point> asteroids = new ArrayList<>();
        List<String> lines = input.collect(Collectors.toList());
        for(int y = 0; y<lines.size(); y++) {
            String line = lines.get(y);
            for(int x = 0; x<line.length(); x++) {
                if(line.charAt(x)=='#') {
                    asteroids.add(new Point(x, y));
                }
            }
        }
        return asteroids;
    }
}
