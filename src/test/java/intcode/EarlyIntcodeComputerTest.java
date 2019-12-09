package intcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EarlyIntcodeComputerTest {

    @Test
    void testIntcodeIO() {
        int[] program = new int[]{3,0,4,0,99};
        assertEquals(42, EarlyIntcodeComputer.runIO(program, 42));
    }

    @Test
    void testIntcodeEqualPosition() {
        int[] program = new int[]{3,9,8,9,10,9,4,9,99,-1,8};
        assertEquals(1, EarlyIntcodeComputer.runIO(program, 8));
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 7));
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 9));
    }

    @Test
    void testIntcodeLessThanPosition() {
        int[] program = new int[]{3,9,7,9,10,9,4,9,99,-1,8};
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 8));
        assertEquals(1, EarlyIntcodeComputer.runIO(program, 7));
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 9));
    }

    @Test
    void testIntcodeEqualImmediate() {
        int[] program = new int[]{3,3,1108,-1,8,3,4,3,99};
        assertEquals(1, EarlyIntcodeComputer.runIO(program, 8));
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 7));
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 9));
    }

    @Test
    void testIntcodeLessThanImmediate() {
        int[] program = new int[]{3, 3, 1107, -1, 8, 3, 4, 3, 99};
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 8));
        assertEquals(1, EarlyIntcodeComputer.runIO(program, 7));
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 9));
    }

    @Test
    void testIntcodeNonZeroPosition() {
        int[] program = new int[]{3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9};
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 0));
        assertEquals(1, EarlyIntcodeComputer.runIO(program, 1));
        assertEquals(1, EarlyIntcodeComputer.runIO(program, -1));
    }

    @Test
    void testIntcodeNonZeroImmediate() {
        int[] program = new int[]{3,3,1105,-1,9,1101,0,0,12,4,12,99,1};
        assertEquals(0, EarlyIntcodeComputer.runIO(program, 0));
        assertEquals(1, EarlyIntcodeComputer.runIO(program, 1));
        assertEquals(1, EarlyIntcodeComputer.runIO(program, 2));
    }

    @Test
    void testIntcodeEight() {
        int[] program = new int[]{3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        assertEquals(999, EarlyIntcodeComputer.runIO(program, 7));
        assertEquals(1000, EarlyIntcodeComputer.runIO(program, 8));
        assertEquals(1001, EarlyIntcodeComputer.runIO(program, 9));
    }
}