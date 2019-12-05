package intcode;

import org.junit.jupiter.api.Test;
import solutions.Day2;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class IntcodeComputerTest {

    @Test
    void testIntcode1() {
        int[] program = new int[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        IntcodeComputer.intcode(program);
        assertArrayEquals(new int[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}, program);
    }

    @Test
    void testIntcode2() {
        int[] program = new int[]{1,0,0,0,99};
        IntcodeComputer.intcode(program);
        assertArrayEquals(new int[]{2,0,0,0,99}, program);
    }

    @Test
    void testIntcode3() {
        int[] program = new int[]{2,3,0,3,99};
        IntcodeComputer.intcode(program);
        assertArrayEquals(new int[]{2,3,0,6,99}, program);
    }

    @Test
    void testIntcode4() {
        int[] program = new int[]{2,4,4,5,99,0};
        IntcodeComputer.intcode(program);
        assertArrayEquals(new int[]{2,4,4,5,99,9801}, program);
    }

    @Test
    void testIntcode5() {
        int[] program = new int[]{1,1,1,4,99,5,6,0,99};
        IntcodeComputer.intcode(program);
        assertArrayEquals(new int[]{30,1,1,4,2,5,6,0,99}, program);
    }

    @Test
    void testRunNounVerb() {
        int[] input = FileUtils.readCommaSeparatedValues("day2/input.txt")
                .mapToInt(Integer::parseInt)
                .toArray();

        assertEquals(6327510, new IntcodeComputer(input).runNounVerb(12, 2));
    }
}