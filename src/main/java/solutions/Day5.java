package solutions;

import intcode.EarlyIntcodeComputer;
import utils.FileUtils;

public class Day5 {

    public static void main(String[] args) {
        int[] program = FileUtils.readCommaSeparatedInts("day5.txt");

        int output = EarlyIntcodeComputer.runIO(program, 1);
        System.out.println("Output for first star: " + output);

        int code = EarlyIntcodeComputer.runIO(program,5);
        System.out.println("Output for second star: " + code);
    }
}
