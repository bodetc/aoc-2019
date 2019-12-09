package intcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntcodeComputerTest {

    @Test
    void testRelativeMode() {
        int[] program = new int[]{109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99};
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run();
        assertArrayEquals(program, computer.getOutput());
    }
}