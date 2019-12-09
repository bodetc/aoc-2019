package intcode;

public class EarlyIntcodeComputer {

    public static int runNounVerb(int[] instructions, int noun, int verb) {
        int[]  program = instructions.clone();
        program[1] = noun;
        program[2] = verb;

        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();

        return computer.getProgram()[0];
    }

    public static int runIO(int[] instructions, int input) {
        IntcodeComputer computer = new IntcodeComputer(instructions);
        computer.run(input);
        return computer.getLastOutput();
    }
}
