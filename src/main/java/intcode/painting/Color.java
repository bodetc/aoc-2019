package intcode.painting;

import utils.classes.ValueBase;

enum Color implements ValueBase<Integer> {
    BLACK(0),
    WHITE(1);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static Color fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }
}
