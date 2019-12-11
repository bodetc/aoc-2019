package geometry;

import intcode.ValueBase;

public enum Direction implements ValueBase<Integer> {
    U(0),
    R(1),
    D(2),
    L(3);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public boolean isHorizontal() {
        return this == R || this == L;
    }

    public boolean isVertical() {
        return this == U || this == D;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public Direction toLeft() {
        return ValueBase.fromValue(values(), Math.floorMod((value - 1), 4));
    }

    public Direction toRight() {
        return ValueBase.fromValue(values(), Math.floorMod((value + 1), 4));
    }
}
