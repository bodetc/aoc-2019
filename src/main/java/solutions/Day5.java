package solutions;

import intcode.IntcodeComputer;
import utils.FileUtils;

public class Day5 {

    public static void main(String[] args) {
        int[] program = FileUtils.readCommaSeparatedInts("day5.txt");
        IntcodeComputer computer = new IntcodeComputer(program);

        int output = computer.runIO(1);
        System.out.println("Output for first star: " + output);

        int code = computer.runIO(5);
        System.out.println("Output for second star: " + code);
    }
}
