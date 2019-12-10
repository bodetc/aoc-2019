package geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static utils.FileUtils.COMMA_SEPARATOR;

public class Line {
    private final List<Segment> segments = new ArrayList<>();

    private Line(List<Vector> path) {
        Point start = new Point(0, 0);

        for (Vector vector : path) {
            Segment segment = new Segment(start, vector);
            segments.add(segment);
            start=segment.end;
        }
    }

    public Line(String path) {
        this(COMMA_SEPARATOR.splitAsStream(path).map(Vector::new).collect(Collectors.toList()));
    }

    public List<Point> intersect(Line other) {
        return segments.stream()
                .flatMap(s1 -> other.segments.stream().map(s1::intersect))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(Predicate.not(Point.ORIGIN::equals)) // Remove origin
                .collect(Collectors.toList());
    }

    public int delay(Point point) {
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
