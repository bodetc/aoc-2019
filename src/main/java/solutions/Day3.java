package solutions;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static utils.FileUtils.COMMA_SEPARATOR;

public class Day3 {
    private enum Direction {
        R, L, U, D;

        private static Direction fromString(String string) {
            switch (string) {
                case "R":
                    return R;
                case "L":
                    return L;
                case "U":
                    return U;
            }
            throw new IllegalStateException("This point cannot be reached.");
        }
    }

    private static class Point {
        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int taxiDistance(Point other) {
            return Math.abs(other.x - x) + Math.abs(other.y - y);
        }

        private boolean equals(Point other) {
            return x==other.x && y==other.y;
        }

        private Point createPoint(PathSegment segment) {
            switch (segment.direction) {
                case R:
                    return new Point(x + segment.distance, y);
                case L:
                    return new Point(x - segment.distance, y);
                case U:
                    return new Point(x, y + segment.distance);
                case D:
                    return new Point(x, y - segment.distance);
            }
            throw new IllegalStateException("This point cannot be reached.");
        }
    }

    private static class PathSegment {
        private final Direction direction;
        private final int distance;

        private PathSegment(Direction direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }

        private PathSegment(String string) {
            this.direction = Direction.valueOf(string.substring(0, 1));
            this.distance = Integer.parseInt(string.substring(1));
        }
    }

    private static class Segment {
        private final Point start, end;

        private Segment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        private Optional<Point> intersect(Segment other) {
            double denom = (other.end.y - other.start.y) * (end.x - start.x) - (other.end.x - other.start.x) * (end.y - start.y);

            if (denom == 0.) {
                // Lines are parallel.
                return Optional.empty();
            }

            double ua = ((other.end.x - other.start.x) * (start.y - other.start.y) - (other.end.y - other.start.y) * (start.x - other.start.x)) / denom;
            double ub = ((end.x - start.x) * (start.y - other.start.y) - (end.y - start.y) * (start.x - other.start.x)) / denom;

            if (ua >= 0. && ua <= 1. && ub >= 0. && ub <= 1.0f) {
                // Get the intersection point.
                return Optional.of(new Point((int) (start.x + ua * (end.x - start.x)), (int) (start.y + ua * (end.y - start.y))));
            } else {
                // Intersection is outside bounds
                return Optional.empty();
            }
        }
    }

    private static class Line {
        private final List<Segment> segments = new ArrayList<>();

        private Line(List<PathSegment> path) {
            Point a = new Point(0, 0);

            for (PathSegment segment : path) {
                Point b = a.createPoint(segment);
                segments.add(new Segment(a, b));
                a = b;
            }
        }

        private Line(String path) {
            this(COMMA_SEPARATOR.splitAsStream(path).map(PathSegment::new).collect(Collectors.toList()));
        }

        private List<Point> intersect(Line other) {
            return segments.stream()
                    .flatMap(s1 -> other.segments.stream().map(s1::intersect))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    private static int findClosestIntersection(Line a, Line b) {
        Point origin = new Point(0, 0);
        return a.intersect(b).stream() // Find intersections
                .filter(Predicate.not(origin::equals)) // Remove origin
                .mapToInt(origin::taxiDistance) // Get distances
                .min() // Get closest distance
                .orElseThrow();
    }

    public static int findClosestIntersection(String a, String b) {
        return findClosestIntersection(new Line(a), new Line(b));
    }

    public static void main(String[] args) {
        List<String> paths = FileUtils.readLines("day3/input.txt").collect(Collectors.toList());

        int taxiDistance = findClosestIntersection( paths.get(0), paths.get(1));
        System.out.println("Manhattan (sic) distance: " + taxiDistance);
    }
}
