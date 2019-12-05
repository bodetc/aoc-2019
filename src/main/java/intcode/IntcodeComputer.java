package intcode;

public class IntcodeComputer {
    private static final int[] EMPTY_ARRAY = new int[]{};

    private final int[] savedProgram;
    private int[] program;

    public IntcodeComputer(int[] savedProgram) {
        this.savedProgram = savedProgram;
    }

    public int runNounVerb(int noun, int verb) {
        program = this.savedProgram.clone();
        program[1] = noun;
        program[2] = verb;

        intcode(program);
        return program[0];
    }

    int[] run() {
        program = this.savedProgram.clone();
        intcode(EMPTY_ARRAY);
        return program;
    }

    private void intcode(int[] inputs) {
        int inputIndex = 0;
        int position = 0;

        while (position < program.length) {
            int instruction = program[position];
            OpCode opcode = OpCode.fromInstruction(instruction);

            if (opcode == OpCode.EXIT) {
                return;
            }


            int pos1 = program[position + 1];
            int pos2 = program[position + 2];
            int posR = program[position + opcode.getOutputOffset()];

            switch (opcode) {
                case ADD:
                    program[posR] = program[pos1] + program[pos2];
                    break;
                case MULTIPLY:
                    program[posR] = program[pos1] * program[pos2];
                    break;
                default:
                    throw new IllegalStateException();
            }
            position+=opcode.getPositionChange();
        }

        throw new IllegalStateException("Intcode savedProgram should end when encountering 99.");
    }
}
