package intcode.arcade;

import utils.classes.ValueBase;

public enum Joystick implements ValueBase<Integer> {
    NEUTRAL(0),
    LEFT(-1),
    RIGHT(1),
    EXIT(99);

    private final int value;

    Joystick(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static Joystick fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }
}
