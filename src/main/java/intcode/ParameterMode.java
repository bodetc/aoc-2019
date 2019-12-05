package intcode;

import java.util.ArrayList;
import java.util.List;

enum ParameterMode implements ValueBase<Integer> {
    POSITION(0),
    IMMEDIATE(1);

    private final int value;

    ParameterMode(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static ParameterMode[] fromInstruction(int instruction, int numberOfInputParameters) {
        List<ParameterMode> modes = new ArrayList<>(numberOfInputParameters);

        instruction = instruction / 100;
        while (instruction > 0) {
            modes.add(ValueBase.fromValue(values(), instruction % 10));
            instruction = instruction / 10;
        }

        while (modes.size()<numberOfInputParameters) {
            modes.add(POSITION);
        }

        return modes.toArray(new ParameterMode[0]);
    }
}
