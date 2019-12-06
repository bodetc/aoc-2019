package intcode;

import org.junit.jupiter.api.Test;
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
        IntcodeComputer computer = new IntcodeComputer(new int[]{3,0,4,0,99});
        assertEquals(42, computer.runIO(42));
    }

    @Test
    void testIntcodeEqualPosition() {
        IntcodeComputer computer = new IntcodeComputer(new int[]{3,9,8,9,10,9,4,9,99,-1,8});
        assertEquals(1, computer.runIO(8));
        assertEquals(0, computer.runIO(7));
        assertEquals(0, computer.runIO(9));
    }

    @Test
    void testIntcodeLessThanPosition() {
        IntcodeComputer computer = new IntcodeComputer(new int[]{3,9,7,9,10,9,4,9,99,-1,8});
        assertEquals(0, computer.runIO(8));
        assertEquals(1, computer.runIO(7));
        assertEquals(0, computer.runIO(9));
    }

    @Test
    void testIntcodeEqualImmediate() {
        IntcodeComputer computer = new IntcodeComputer(new int[]{3,3,1108,-1,8,3,4,3,99});
        assertEquals(1, computer.runIO(8));
        assertEquals(0, computer.runIO(7));
        assertEquals(0, computer.runIO(9));
    }

    @Test
    void testIntcodeLessThanImmediate() {
        IntcodeComputer computer = new IntcodeComputer(new int[]{3, 3, 1107, -1, 8, 3, 4, 3, 99});
        assertEquals(0, computer.runIO(8));
        assertEquals(1, computer.runIO(7));
        assertEquals(0, computer.runIO(9));
    }

    @Test
    void testIntcodeNonZeroPosition() {
        IntcodeComputer computer = new IntcodeComputer(new int[]{3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9});
        assertEquals(0, computer.runIO(0));
        assertEquals(1, computer.runIO(1));
        assertEquals(1, computer.runIO(-1));
    }

    @Test
    void testIntcodeNonZeroImmediate() {
        IntcodeComputer computer = new IntcodeComputer(new int[]{3,3,1105,-1,9,1101,0,0,12,4,12,99,1});
        assertEquals(0, computer.runIO(0));
        assertEquals(1, computer.runIO(1));
        assertEquals(1, computer.runIO(2));
    }

    @Test
    void testIntcodeEight() {
        IntcodeComputer computer = new IntcodeComputer(new int[]{3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99});
        assertEquals(999, computer.runIO(7));
        assertEquals(1000, computer.runIO(8));
        assertEquals(1001, computer.runIO(9));
    }
}