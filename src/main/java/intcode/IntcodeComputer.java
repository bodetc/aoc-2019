package intcode;

import java.util.ArrayList;
import java.util.List;

public class IntcodeComputer {

    private enum ReturnReason {
        EXIT,
        WAIT_FOR_INPUT,
    }

    private final Program program;
    private final List<Integer> output = new ArrayList<>();
    private int position = 0;

    public IntcodeComputer(int[] instructions) {
        this.program = new Program(instructions);
    }

    public ReturnReason run() {
        return run(null);
    }

    public ReturnReason run(Integer input) {
        while (true) {
            int instruction = program.get(position);
            OpCode opcode = OpCode.fromInstruction(instruction);

            if (opcode == OpCode.EXIT) {
                return ReturnReason.EXIT;
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
                    if(input!=null) {
                        program.set(outputPosition, ParameterMode.POSITION, input);
                        input = null;
                    } else {
                        return ReturnReason.WAIT_FOR_INPUT;
                    }
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

    public int[] getProgram() {
        return program.toArray();
    }

    public int[] getOutput() {
        return output.stream().mapToInt(Integer::intValue).toArray();
    }

    public int getLastOutput() {
        return output.get(output.size()-1);
    }
}
