package solutions;

import intcode.IntcodeComputer;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void regressionTestFirstStar() {
        int[] input = FileUtils.readCommaSeparatedValues("day2.txt")
                .mapToInt(Integer::parseInt)
                .toArray();

        int value = new IntcodeComputer(input).runNounVerb(12, 2);
        assertEquals(6327510, value);
    }

    @Test
    void regressionTestSecondStar() {
        int[] input = FileUtils.readCommaSeparatedValues("day2.txt")
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] result = Day2.searchIntcode(input, 19690720);

        int noun=result[0];
        int verb=result[1];

        assertEquals(41, noun);
        assertEquals(12, verb);
    }
}