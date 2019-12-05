package intcode;

enum OpCode implements ValueBase<Integer> {
    ADD(1, 2, 1),
    MULTIPLY(2, 2, 1),
    INPUT(3, 0, 1),
    OUTPUT(4, 1, 0),
    JUMP_IF_TRUE(5, 2, 0, true),
    JUMP_IF_FALSE(6, 2, 0, true),
    LESS_THAN(7, 2, 1),
    EQUALS(8, 2, 1),
    EXIT(99, 0, 0);

    private final int value;
    private final int nbInputParam;
    private final int nbOutputParam;
    private final boolean preventPositionChange;

    OpCode(int value, int nbInputParam, int nbOutputParam, boolean preventPositionChange) {
        this.value = value;
        this.nbInputParam = nbInputParam;
        this.nbOutputParam = nbOutputParam;
        this.preventPositionChange = preventPositionChange;
    }

    OpCode(int value, int nbInputParam, int nbOutputParam) {
        this(value, nbInputParam, nbOutputParam, false);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public int getNumberOfInputParameters() {
        return nbInputParam;
    }

    public int getOutputOffset() {
        return nbOutputParam > 0 ? nbInputParam + 1 : 0;
    }

    public int getPositionChange() {
        return preventPositionChange ? 0 : nbInputParam + nbOutputParam + 1;
    }

    public static OpCode fromInstruction(int instruction) {
        return ValueBase.fromValue(values(), instruction % 100);
    }
}
