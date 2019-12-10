package solutions;

import geometry.Line;
import geometry.Point;
import utils.FileUtils;

import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Day03 {

    private static int findIntersection(Line a, Line b, ToIntFunction<Point> distance) {
        return a.intersect(b).stream() // Find intersections
                .mapToInt(distance) // Get distances
                .min() // Get closest distance
                .orElseThrow();
    }

    static int findClosestIntersection(String a, String b) {
        return findIntersection(new Line(a), new Line(b), Point.ORIGIN::taxiDistance);
    }

    static int findSmallestDelayIntersection(String a, String b) {
        Line lineA = new Line(a);
        Line lineB = new Line(b);
        return findIntersection(lineA, lineB, point -> lineA.delay(point) + lineB.delay(point));
    }

    public static void main(String[] args) {
        List<String> paths = FileUtils.readLines("day3/input.txt").collect(Collectors.toList());

        int taxiDistance = findClosestIntersection(paths.get(0), paths.get(1));
        System.out.println("Manhattan (sic) distance: " + taxiDistance);

        int smallestDelay = findSmallestDelayIntersection(paths.get(0), paths.get(1));
        System.out.println("Smallest delay: " + smallestDelay);
    }
}
