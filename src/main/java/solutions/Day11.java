package solutions;

import helpers.PaintingRobot;
import intcode.IntcodeComputer;
import utils.FileUtils;
import utils.HashMapWithDefault;

public class Day11 {
    public static void main(String[] args) {
        long[] program = FileUtils.readCommaSeparatedLongs("day11.txt");
        PaintingRobot robot = new PaintingRobot(program);
        robot.run();

        long paintedTiles = robot.getPaintedTiles();
        System.out.println("Output for first star: " + paintedTiles);
        robot.printHull();

        PaintingRobot newRobot = new PaintingRobot(program, true);
        newRobot.run();
        System.out.println("Output for second star: ");
        newRobot.printHull();
    }
}
