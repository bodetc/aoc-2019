package geometry;

public enum Direction {
    R, L, U, D;

    public boolean isHorizontal() {
        return this == R || this == L;
    }

    public boolean isVertical() {
        return this == U || this == D;
    }
}
