package solutions;

import intcode.EarlyIntcodeComputer;
import utils.FileUtils;

public class Day05 {

    public static void main(String[] args) {
        long[] program = FileUtils.readCommaSeparatedLongs("day05.txt");

        long output = EarlyIntcodeComputer.runIO(program, 1);
        System.out.println("Output for first star: " + output);

        long code = EarlyIntcodeComputer.runIO(program,5);
        System.out.println("Output for second star: " + code);
    }
}
