package intcode;

import java.util.Arrays;
import java.util.Optional;

enum OpCode implements ValueBase<Integer> {
    ADD(1, 2) ,
    MULTIPLY(2, 2),
    EXIT(99, 0);

    private final int value;
    private final int inputParameterCount;


    OpCode(int value, int inputParameterCount) {
        this.value = value;
        this.inputParameterCount = inputParameterCount;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public int getInputParameterCount() {
        return inputParameterCount;
    }

    public int getOutputOffset() {
        return inputParameterCount +1;
    }

    public int getPositionChange() {
        return inputParameterCount +2;
    }

    public static OpCode fromInstruction(int instruction) {
        return ValueBase.fromValue(values(), instruction%100);
    }
}
