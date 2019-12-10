package geometry;

public class Point {
    final int x;
    final int y;

    public static final Point ORIGIN = new Point(0, 0);

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int taxiDistance(Point other) {
        return Math.abs(other.x - x) + Math.abs(other.y - y);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Point && equals((Point)other);
    }

    private boolean equals(Point other) {
        return x == other.x && y == other.y;
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
}
