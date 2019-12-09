package solutions;

import intcode.EarlyIntcodeComputer;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void regressionTestFirstStar() {
        long[] input = FileUtils.readCommaSeparatedLongs("day2.txt");

        long value = EarlyIntcodeComputer.runNounVerb(input,12, 2);
        assertEquals(6327510, value);
    }

    @Test
    void regressionTestSecondStar() {
        long[] input = FileUtils.readCommaSeparatedLongs("day2.txt");

        int[] result = Day2.searchIntcode(input, 19690720);

        int noun=result[0];
        int verb=result[1];

        assertEquals(41, noun);
        assertEquals(12, verb);
    }
}