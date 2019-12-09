package intcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EarlyTests {

    static long[] runProgram(long[] instructions) {
        IntcodeComputer computer = new IntcodeComputer(instructions);
        computer.run();
        return computer.getProgram();
    }

    @Test
    void testIntcode1() {
        long[] program = new long[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        assertArrayEquals(new long[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}, runProgram(program));
    }

    @Test
    void testIntcode2() {
        long[] program = new long[]{1,0,0,0,99};
        assertArrayEquals(new long[]{2,0,0,0,99}, runProgram(program));
    }

    @Test
    void testIntcode3() {
        long[] program = new long[]{2,3,0,3,99};
        assertArrayEquals(new long[]{2,3,0,6,99}, runProgram(program));
    }

    @Test
    void testIntcode4() {
        long[] program = new long[]{2,4,4,5,99,0};
        assertArrayEquals(new long[]{2,4,4,5,99,9801}, runProgram(program));
    }

    @Test
    void testIntcode5() {
        long[] program = new long[]{1,1,1,4,99,5,6,0,99};
        assertArrayEquals(new long[]{30,1,1,4,2,5,6,0,99}, runProgram(program));
    }

    @Test
    void testIntcode6() {
        long[] program = new long[]{1002,4,3,4,33};
        assertArrayEquals(new long[]{1002,4,3,4,99}, runProgram(program));
    }

    @Test
    void testIntcodeNegative() {
        long[] program = new long[]{1101,100,-1,4,0};
        assertArrayEquals(new long[]{1101,100,-1,4,99}, runProgram(program));
    }
}