package geometry;

class Vector {
    final Direction direction;
    final int distance;

    Vector(Vector other) {
        this.direction = other.direction;
        this.distance = other.distance;
    }

    Vector(String vector) {
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
