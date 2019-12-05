package intcode;

public class IntcodeComputer {
    private final int[] program;

    public IntcodeComputer(int[] program) {
        this.program = program;
    }

    static void intcode(int[] program) {
        for (int pos = 0; pos < program.length; pos += 4) {
            int opcode = program[pos];

            if (opcode == 99) {
                return;
            }

            int pos1 = program[pos + 1];
            int pos2 = program[pos + 2];
            int posR = program[pos + 3];

            if (opcode == 1) {
                program[posR] = program[pos1] + program[pos2];
            } else if (opcode == 2) {
                program[posR] = program[pos1] * program[pos2];
            } else {
                throw new IllegalStateException("Encountering an unknown opcode means something went wrong.");
            }
        }
        throw new IllegalStateException("Intcode program should end when encountering 99.");
    }

    public int runNounVerb(int noun, int verb) {
        int[] program = this.program.clone();
        program[1]=noun;
        program[2]=verb;

        intcode(program);
        return program[0];
    }
}
