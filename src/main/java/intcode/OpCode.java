package intcode;

import java.util.Arrays;
import java.util.Optional;

enum OpCode implements ValueBase<Integer> {
    ADD(1, 2) ,
    MULTIPLY(2, 2),
    EXIT(99, 0);

    private final int value;
    private final int numberOfInputParameters;


    OpCode(int value, int numberOfInputParameters) {
        this.value = value;
        this.numberOfInputParameters = numberOfInputParameters;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public int getNumberOfInputParameters() {
        return numberOfInputParameters;
    }

    public int getOutputOffset() {
        return numberOfInputParameters +1;
    }

    public int getPositionChange() {
        return numberOfInputParameters +2;
    }

    public static OpCode fromInstruction(int instruction) {
        return ValueBase.fromValue(values(), instruction%100);
    }
}
