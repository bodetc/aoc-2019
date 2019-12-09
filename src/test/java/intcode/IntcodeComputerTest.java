package intcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntcodeComputerTest {

    @Test
    void testRelativeMode() {
        long[] program = new long[]{109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99};
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        assertArrayEquals(program, computer.getOutput());
    }

    @Test
    void testLargeOuput() {
        long[] program = new long[]{1102,34915192,34915192,7,4,7,99,0};
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        assertEquals(1219070632396864L, computer.getLastOutput());
    }


    @Test
    void testLargeInput() {
        long[] program = new long[]{104,1125899906842624L,99};
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        assertEquals(1125899906842624L, computer.getLastOutput());
    }
}