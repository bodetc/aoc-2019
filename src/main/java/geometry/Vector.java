package geometry;

public class Vector {
    final Direction direction;
    final int distance;

    public Vector(Direction direction, int distance) {
        this.direction = direction;
        this.distance = distance;
    }

    Vector(Vector other) {
        this(other.direction, other.distance);
    }

    Vector(String vector) {
        this(Direction.valueOf(vector.substring(0, 1)),
                Integer.parseInt(vector.substring(1)));
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
