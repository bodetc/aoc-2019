package intcode.painting;

import utils.classes.PrintCharacter;
import utils.classes.ValueBase;

enum Color implements ValueBase<Integer>, PrintCharacter {
    BLACK(0, ' '),
    WHITE(1, '█');

    private final int value;
    private final char print;

    Color(int value, char print) {
        this.value = value;
        this.print = print;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public char getPrintChar() {
        return print;
    }

    public static Color fromValue(int value) {
        return ValueBase.fromValue(values(), value);
    }
}
