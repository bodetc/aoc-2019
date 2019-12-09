package intcode;

import utils.MathUtils;

import java.util.HashMap;
import java.util.Map;

public class Program {
    private final Map<Integer, Integer> program;

    private int relativeBase = 0;

    public Program(int[] initialProgram) {
        program = new HashMap<>();
        for (int i = 0; i < initialProgram.length; i++) {
            program.put(i, initialProgram[i]);
        }
    }

    public Integer get(int index) {
        return get(index, ParameterMode.IMMEDIATE);
    }

    public int get(int index, ParameterMode mode) {
        return MathUtils.nullToZero(getUnboxed(index, mode));
    }

    private Integer getUnboxed(int index, ParameterMode mode) {
        switch (mode) {
            case POSITION:
                return program.get(program.get(index));
            case IMMEDIATE:
                return program.get(index);
            case RELATIVE:
                return program.get(relativeBase + program.get(index));
        }
        throw new IllegalStateException("Unsupported mode");
    }

    public void set(int index, int value) {
        set(index, ParameterMode.POSITION, value);
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
                program.put(relativeBase + program.get(index), value);
                break;
        }
    }

    public void offsetRelativeBase(int offset) {
        this.relativeBase += offset;
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
