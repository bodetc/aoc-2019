package intcode;

import org.junit.jupiter.api.Test;
import solutions.Day2;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class IntcodeComputerTest {

    @Test
    void testIntcode1() {
        int[] program = new int[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        assertArrayEquals(new int[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}, new IntcodeComputer(program).run());
    }

    @Test
    void testIntcode2() {
        int[] program = new int[]{1,0,0,0,99};
        assertArrayEquals(new int[]{2,0,0,0,99}, new IntcodeComputer(program).run());
    }

    @Test
    void testIntcode3() {
        int[] program = new int[]{2,3,0,3,99};
        assertArrayEquals(new int[]{2,3,0,6,99}, new IntcodeComputer(program).run());
    }

    @Test
    void testIntcode4() {
        int[] program = new int[]{2,4,4,5,99,0};
        assertArrayEquals(new int[]{2,4,4,5,99,9801}, new IntcodeComputer(program).run());
    }

    @Test
    void testIntcode5() {
        int[] program = new int[]{1,1,1,4,99,5,6,0,99};
        assertArrayEquals(new int[]{30,1,1,4,2,5,6,0,99}, new IntcodeComputer(program).run());
    }

    @Test
    void testIntcode6() {
        int[] program = new int[]{1002,4,3,4,33};
        assertArrayEquals(new int[]{1002,4,3,4,99}, new IntcodeComputer(program).run());
    }

    @Test
    void testIntcodeNegative() {
        int[] program = new int[]{1101,100,-1,4,0};
        assertArrayEquals(new int[]{1101,100,-1,4,99}, new IntcodeComputer(program).run());
    }

    @Test
    void testIntcodeIO() {
        int[] program = new int[]{3,0,4,0,99};
        assertEquals(42, new IntcodeComputer(program).runIO(42));
    }

    @Test
    void testRunNounVerb() {
        int[] input = FileUtils.readCommaSeparatedValues("day2/input.txt")
                .mapToInt(Integer::parseInt)
                .toArray();

        assertEquals(6327510, new IntcodeComputer(input).runNounVerb(12, 2));
    }
}