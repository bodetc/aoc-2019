package intcode;

import java.util.ArrayList;
import java.util.List;

public class IntcodeComputer {

    private final int[] savedProgram;

    public IntcodeComputer(int[] savedProgram) {
        this.savedProgram = savedProgram;
    }

    public int runNounVerb(int noun, int verb) {
        int[] program = this.savedProgram.clone();
        program[1] = noun;
        program[2] = verb;

        intcode(program, new int[]{});
        return program[0];
    }

    int[] run() {
        int[] program = this.savedProgram.clone();
        intcode(program, new int[]{});
        return program;
    }

    public int runIO(int input) {
        int[] program = this.savedProgram.clone();
        int[] output = intcode(program, new int[]{input});
        return output[output.length - 1];
    }

    public int[] runIO(int[] input) {
        int[] program = this.savedProgram.clone();
        return intcode(program, input);
    }

    private static int[] intcode(int[] program, int[] input) {
        int position = 0;
        int inputPosition = 0;
        List<Integer> output = new ArrayList<>();

        while (position < program.length) {
            int instruction = program[position];
            OpCode opcode = OpCode.fromInstruction(instruction);

            if (opcode == OpCode.EXIT) {
                return output.stream().mapToInt(Integer::intValue).toArray();
            }

            int numberOfInputParameters = opcode.getNumberOfInputParameters();
            ParameterMode[] modes = ParameterMode.fromInstruction(instruction, numberOfInputParameters);

            int[] parameters = new int[numberOfInputParameters];
            for (int i = 0; i < numberOfInputParameters; i++) {
                int parameterPosition = position + i + 1;
                parameters[i] = modes[i] == ParameterMode.IMMEDIATE ? program[parameterPosition] : program[program[parameterPosition]];
            }

            int outputPosition = program[position + opcode.getOutputOffset()];

            switch (opcode) {
                case ADD:
                    program[outputPosition] = parameters[0] + parameters[1];
                    break;
                case MULTIPLY:
                    program[outputPosition] = parameters[0] * parameters[1];
                    break;
                case INPUT:
                    program[outputPosition] = input[inputPosition];
                    inputPosition++;
                    break;
                case OUTPUT:
                    output.add(parameters[0]);
                    break;
                case JUMP_IF_TRUE:
                    if (parameters[0] > 0) {
                        position = parameters[1];
                    } else {
                        position += 3;
                    }
                    break;
                case JUMP_IF_FALSE:
                    if (parameters[0] == 0) {
                        position = parameters[1];
                    } else {
                        position += 3;
                    }
                    break;
                case LESS_THAN:
                    program[outputPosition] = parameters[0] < parameters[1] ? 1 : 0;
                    break;
                case EQUALS:
                    program[outputPosition] = parameters[0] == parameters[1] ? 1 : 0;
                    break;
                default:
                    throw new IllegalStateException();
            }
            position += opcode.getPositionChange();
        }

        throw new IllegalStateException("Intcode program should end when encountering 99.");
    }
}
