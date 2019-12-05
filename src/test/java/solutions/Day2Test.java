package solutions;

import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    void testSearchIntcode() {
        int[] input = FileUtils.readCommaSeparatedValues("day2/input.txt")
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] result = Day2.searchIntcode(input, 19690720);

        int noun=result[0];
        int verb=result[1];

        assertEquals(41, noun);
        assertEquals(12, verb);
    }
}