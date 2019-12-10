package solutions;

import intcode.IntcodeComputer;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    @Test
    void regressionTestFirstStar() {
        long[] program = FileUtils.readCommaSeparatedLongs("day09.txt");
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run(1);

        assertEquals(2752191671L, computer.getLastOutput());
    }

    @Test
    void regressionTestSecondStar() {
        long[] program = FileUtils.readCommaSeparatedLongs("day09.txt");
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.run(2);

        assertEquals(87571, computer.getLastOutput());
    }
}