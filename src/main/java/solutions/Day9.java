package solutions;

import intcode.IntcodeComputer;
import utils.FileUtils;

public class Day9 {
    public static void main(String[] args) {
        long[] program = FileUtils.readCommaSeparatedLongs("day9.txt");
        IntcodeComputer computer = new IntcodeComputer(program);

        computer.run(1);
        for (long l : computer.getOutput()) {
            System.out.println(l);
        }

        long keycode = computer.getLastOutput();
        System.out.println("Output for first star: " + keycode);

    }
}
