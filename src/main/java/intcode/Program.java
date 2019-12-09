package intcode;

import java.util.HashMap;
import java.util.Map;

public class Program {
    private final Map<Integer, Integer> program;

    private int relativeBaseOffset = 0;


    public Program(int[] initialProgram) {
        program = new HashMap<>();
        for (int i = 0; i < initialProgram.length; i++) {
            program.put(i, initialProgram[i]);
        }
    }

    public Integer get(int index) {
        return get(index, ParameterMode.IMMEDIATE);
    }

    public Integer get(int index, ParameterMode mode) {
        switch (mode) {
            case POSITION:
                return program.get(program.get(index));
            case IMMEDIATE:
                return program.get(index);
            case RELATIVE:
                return program.get(index + relativeBaseOffset + program.get(index));
        }
        throw new IllegalStateException("Unsupported mode");
    }

    public void set(int index, int value) {
        set(index, ParameterMode.IMMEDIATE, value);
    }

    public void set(int index, ParameterMode mode, int value) {
        switch (mode) {
            case POSITION:
                program.put(program.get(index), value);
                break;
            case IMMEDIATE:
                program.put(index, value);
                break;
            case RELATIVE:
                program.put(index + relativeBaseOffset + program.get(index), value);
                break;
        }
    }

    public void setRelativeBaseOffset(int relativeBaseOffset) {
        this.relativeBaseOffset = relativeBaseOffset;
    }

    public int[] toArray() {
        int maxValue = program.keySet().stream().max(Integer::compareTo).orElseThrow() + 1;
        int[] array = new int[maxValue];
        for (int i = 0; i < maxValue; i++) {
            array[i] = program.get(i);
        }
        return array;
    }
}
