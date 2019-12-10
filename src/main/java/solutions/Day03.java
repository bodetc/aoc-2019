package solutions;

import utils.FileUtils;
import utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static utils.FileUtils.COMMA_SEPARATOR;

public class Day03 {
    private enum Direction {
        R, L, U, D;

        private boolean isHorizontal() {
            return this == R || this == L;
        }

        private boolean isVertical() {
            return this == U || this == D;
        }
    }

    private static class Point {
        private final int x, y;

        private static final Point ORIGIN = new Point(0, 0);

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int taxiDistance(Point other) {
            return Math.abs(other.x - x) + Math.abs(other.y - y);
        }

        private boolean equals(Point other) {
            return x == other.x && y == other.y;
        }

        private Point addVector(Vector vector) {
            switch (vector.direction) {
                case R:
                    return new Point(x + vector.distance, y);
                case L:
                    return new Point(x - vector.distance, y);
                case U:
                    return new Point(x, y + vector.distance);
                case D:
                    return new Point(x, y - vector.distance);
            }
            throw new IllegalStateException("This point cannot be reached.");
        }
    }

    private static class Vector {
        private final Direction direction;
        private final int distance;

        private Vector(Vector other) {
            this.direction = other.direction;
            this.distance = other.distance;
        }

        private Vector(String vector) {
            this.direction = Direction.valueOf(vector.substring(0, 1));
            this.distance = Integer.parseInt(vector.substring(1));
        }

        boolean isHorizontal() {
            return direction.isHorizontal();
        }

        boolean isVertical() {
            return direction.isVertical();
        }

        int length() {
            return distance;
        }
    }

    private static class Segment extends Vector {
        private final Point start, end;

        private Segment(Point start, Vector vector) {
            super(vector);
            this.start = start;
            this.end = start.addVector(this);
        }

        private Optional<Point> intersect(Segment other) {
            if (isHorizontal() && other.isVertical()
                    && MathUtils.isBetween(start.x, end.x, other.start.x)
                    && MathUtils.isBetween(other.start.y, other.end.y, start.y)) {
                return Optional.of(new Point(other.start.x, start.y));
            } else if (isVertical() && other.isHorizontal()
                    && MathUtils.isBetween(other.start.x, other.end.x, start.x)
                    && MathUtils.isBetween(start.y, end.y, other.start.y)) {
                return Optional.of(new Point(start.x, other.start.y));
            } else {
                return Optional.empty();
            }
        }

        private boolean contains(Point point) {
            return (isHorizontal() && start.y==point.y && MathUtils.isBetween(start.x, end.x, point.x))
                   ||  (isVertical() && start.x==point.x && MathUtils.isBetween(start.y, end.y, point.y));
        }
    }

    private static class Line {
        private final List<Segment> segments = new ArrayList<>();

        private Line(List<Vector> path) {
            Point start = new Point(0, 0);

            for (Vector vector : path) {
                Segment segment = new Segment(start, vector);
                segments.add(segment);
                start=segment.end;
            }
        }

        private Line(String path) {
            this(COMMA_SEPARATOR.splitAsStream(path).map(Vector::new).collect(Collectors.toList()));
        }

        private List<Point> intersect(Line other) {
            return segments.stream()
                    .flatMap(s1 -> other.segments.stream().map(s1::intersect))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(Predicate.not(Point.ORIGIN::equals)) // Remove origin
                    .collect(Collectors.toList());
        }

        private int delay(Point point) {
            int delay = 0;
            for (Segment segment : segments) {
                if (segment.contains(point)) {
                    delay += point.taxiDistance(segment.start);
                    return delay;
                } else {
                    delay += segment.length();
                }
            }
            return delay;
        }
    }


    private static int findIntersection(Line a, Line b, ToIntFunction<Point> distance) {
        return a.intersect(b).stream() // Find intersections
                .mapToInt(distance) // Get distances
                .min() // Get closest distance
                .orElseThrow();
    }

    private static int findClosestIntersection(Line a, Line b) {
        Point origin = new Point(0, 0);
        return findIntersection(a, b, origin::taxiDistance);
    }

    static int findClosestIntersection(String a, String b) {
        return findClosestIntersection(new Line(a), new Line(b));
    }

    private static int findSmallestDelayIntersection(Line a, Line b) {
        return findIntersection(a, b, point -> a.delay(point) + b.delay(point));
    }

    static int findSmallestDelayIntersection(String a, String b) {
        return findSmallestDelayIntersection(new Line(a), new Line(b));
    }

    public static void main(String[] args) {
        List<String> paths = FileUtils.readLines("day3/input.txt").collect(Collectors.toList());

        int taxiDistance = findClosestIntersection(paths.get(0), paths.get(1));
        System.out.println("Manhattan (sic) distance: " + taxiDistance);

        int smallestDelay = findSmallestDelayIntersection(paths.get(0), paths.get(1));
        System.out.println("Smallest delay: " + smallestDelay);
    }
}
