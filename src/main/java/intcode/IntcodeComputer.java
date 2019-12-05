package intcode;

public class IntcodeComputer {

    private final int[] savedProgram;
    private int[] program;

    public IntcodeComputer(int[] savedProgram) {
        this.savedProgram = savedProgram;
    }

    public int runNounVerb(int noun, int verb) {
        program = this.savedProgram.clone();
        program[1] = noun;
        program[2] = verb;

        intcode(0);
        return program[0];
    }

    int[] run() {
        program = this.savedProgram.clone();
        intcode(0);
        return program;
    }

    int runIO(int input) {
        program = this.savedProgram.clone();
        return intcode(input);
    }

    private int intcode(int input) {
        int position = 0;
        int output = 0;

        while (position < program.length) {
            int instruction = program[position];
            OpCode opcode = OpCode.fromInstruction(instruction);

            if (opcode == OpCode.EXIT) {
                return output;
            }

            int numberOfInputParameters = opcode.getNumberOfInputParameters();
            ParameterMode[] modes = ParameterMode.fromInstruction(instruction, numberOfInputParameters);

            int[] parameters = new int[numberOfInputParameters];
            for(int i = 0; i<numberOfInputParameters; i++) {
                int parameterPosition = position + i + 1;
                parameters[i] = modes[i]==ParameterMode.IMMEDIATE ? program[parameterPosition] : program[program[parameterPosition]];
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
                    program[outputPosition] = input;
                    break;
                case OUTPUT:
                    System.out.println("output: " + parameters[0]);
                    output = parameters[0];
                    break;
                default:
                    throw new IllegalStateException();
            }
            position+=opcode.getPositionChange();
        }

        throw new IllegalStateException("Intcode savedProgram should end when encountering 99.");
    }
}
