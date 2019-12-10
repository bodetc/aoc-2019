package geometry;

import utils.MathUtils;

import java.util.Optional;

class Segment extends Vector {
    final Point start;
    final Point end;

    Segment(Point start, Vector vector) {
        super(vector);
        this.start = start;
        this.end = start.addVector(this);
    }

    Optional<Point> intersect(Segment other) {
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

    boolean contains(Point point) {
        return (isHorizontal() && start.y==point.y && MathUtils.isBetween(start.x, end.x, point.x))
               ||  (isVertical() && start.x==point.x && MathUtils.isBetween(start.y, end.y, point.y));
    }
}
