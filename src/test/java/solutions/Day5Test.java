package solutions;

import intcode.IntcodeComputer;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void regressionTestFirstStar() {
        int[] program = FileUtils.readCommaSeparatedValues("day5.txt")
                .mapToInt(Integer::parseInt)
                .toArray();
        IntcodeComputer computer = new IntcodeComputer(program);

        assertEquals(13787043, computer.runIO(1));
    }

    @Test
    void regressionTestSecondStar() {
        int[] program = FileUtils.readCommaSeparatedValues("day5.txt")
                .mapToInt(Integer::parseInt)
                .toArray();
        IntcodeComputer computer = new IntcodeComputer(program);

        assertEquals(3892695, computer.runIO(5));
    }
}