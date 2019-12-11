package solutions;

import helpers.PaintingRobot;
import intcode.IntcodeComputer;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {
    @Test
    void regressionTestFirstStar() {
        long[] program = FileUtils.readCommaSeparatedLongs("day11.txt");
        PaintingRobot robot = new PaintingRobot(program);
        robot.run();

        long paintedTiles = robot.getPaintedTiles();
        assertEquals(2082, paintedTiles);
    }
}