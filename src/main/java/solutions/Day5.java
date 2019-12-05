package solutions;

import intcode.IntcodeComputer;
import utils.FileUtils;

public class Day5 {

    public static void main(String[] args) {
        int[] program = FileUtils.readCommaSeparatedValues("day5/input.txt")
                .mapToInt(Integer::parseInt)
                .toArray();
        IntcodeComputer computer = new IntcodeComputer(program);

        int output = computer.runIO(1);
        System.out.println("Output for first star: " + output);
    }
}

