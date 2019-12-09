package intcode;

import java.util.ArrayList;
import java.util.List;

public class IntcodeComputer {

    private final int[] savedProgram;

    public IntcodeComputer(int[] savedProgram) {
        this.savedProgram = savedProgram;
    }

    public int runNounVerb(int noun, int verb) {
        Program program = new Program(savedProgram);
        program.set(1, noun);
        program.set(2, verb);

        intcode(program, new int[]{});
        return program.get(0, ParameterMode.IMMEDIATE);
    }

    int[] run() {
        Program program = new Program(savedProgram);
        intcode(program, new int[]{});
        return program.toArray();
    }

    public int runIO(int input) {
        Program program = new Program(savedProgram);
        int[] output = intcode(program, new int[]{input});
        return output[output.length - 1];
    }

    public int[] runIO(int[] input) {
        Program program = new Program(savedProgram);
        return intcode(program, input);
    }

    private static int[] intcode(Program program, int[] input) {
        List<Integer> output = new ArrayList<>();

        int position = 0;
        int inputPosition = 0;

        while (true) {
            int instruction = program.get(position);
            OpCode opcode = OpCode.fromInstruction(instruction);

            if (opcode == OpCode.EXIT) {
                return output.stream().mapToInt(Integer::intValue).toArray();
            }

            int numberOfInputParameters = opcode.getNumberOfInputParameters();
            ParameterMode[] modes = ParameterMode.fromInstruction(instruction, numberOfInputParameters);

            int[] parameters = new int[numberOfInputParameters];
            for (int i = 0; i < numberOfInputParameters; i++) {
                int parameterPosition = position + i + 1;
                parameters[i] = program.get(parameterPosition, modes[i]);
            }

            int outputPosition = position + opcode.getOutputOffset();
            boolean hasJumped = false;

            switch (opcode) {
                case ADD:
                    program.set(outputPosition, ParameterMode.POSITION, parameters[0] + parameters[1]);
                    break;
                case MULTIPLY:
                    program.set(outputPosition, ParameterMode.POSITION, parameters[0] * parameters[1]);
                    break;
                case INPUT:
                    program.set(outputPosition, ParameterMode.POSITION, input[inputPosition]);
                    inputPosition++;
                    break;
                case OUTPUT:
                    output.add(parameters[0]);
                    break;
                case JUMP_IF_TRUE:
                    if (parameters[0] > 0) {
                        position = parameters[1];
                        hasJumped = true;
                    }
                    break;
                case JUMP_IF_FALSE:
                    if (parameters[0] == 0) {
                        position = parameters[1];
                        hasJumped = true;
                    }
                    break;
                case LESS_THAN:
                    program.set(outputPosition, ParameterMode.POSITION, parameters[0] < parameters[1] ? 1 : 0);
                    break;
                case EQUALS:
                    program.set(outputPosition, ParameterMode.POSITION, parameters[0] == parameters[1] ? 1 : 0);
                    break;
                case RELATIVE_BASE_OFFSET:
                    program.setRelativeBaseOffset(parameters[0]);
                    break;
                default:
                    throw new IllegalStateException();
            }
            if (!hasJumped) {
                position += opcode.getPositionChange();
            }
        }
    }
}
