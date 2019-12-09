package solutions;

import intcode.IntcodeComputer;
import utils.FileUtils;

public class Day9 {
    public static void main(String[] args) {
        long[] program = FileUtils.readCommaSeparatedLongs("day9.txt");
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run(1);

        long keycode = computer.getLastOutput();
        System.out.println("Output for first star: " + keycode);

        IntcodeComputer boostComputer = new IntcodeComputer(program);
        boostComputer.run(2);

        long coordinates = boostComputer.getLastOutput();
        System.out.println("Output for second star: " + coordinates);
    }
}
