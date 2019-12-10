package geometry;

import utils.MathUtils;

public class Point {
    final int x;
    final int y;

    public static final Point ORIGIN = new Point(0, 0);

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int taxiDistance(Point other) {
        return Math.abs(other.x - x) + Math.abs(other.y - y);
    }

    Point addVector(Vector vector) {
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

    public boolean isCollinear(Point a, Point b) {
        // Area of the triangle ABC (multiplied by 2)
        int area = a.x * (b.y - y) + b.x * (y - a.y) + x * (a.y - b.y);

        return area == 0;
    }

    public boolean isBetween(Point a, Point b) {
        return MathUtils.isBetween(a.x, b.x, x)
                && MathUtils.isBetween(a.y, b.y, y);
    }

    private boolean equals(Point other) {
        return x == other.x && y == other.y;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Point && equals((Point) other);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
