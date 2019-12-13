package intcode.arcade;

import geometry.Point;
import intcode.IntcodeComputer;
import utils.classes.HashMapWithDefault;
import utils.classes.PrintCharacter;

import java.util.Map;

public class ArcadeCabinet {
    private final IntcodeComputer computer;
    private final Map<Point, Tile> screen = new HashMapWithDefault<>(Tile.EMPTY);
    private int score = 0;

    public ArcadeCabinet(long[] instructions) {
        computer = new IntcodeComputer(instructions);
    }

    public void run() {
        run(Joystick.NEUTRAL);
    }

    public void run(Joystick joystick) {
        computer.run(joystick.getValue());
        long[] output = computer.getOutput();

        for (int i = 0; i < output.length; i += 3) {
            int x = Math.toIntExact(output[i]);
            int y = Math.toIntExact(output[i + 1]);
            int z = Math.toIntExact(output[i + 2]);
            if (x == -1 && y == 0) {
                score = z;
            } else {
                screen.put(new Point(x, y), Tile.fromValue(z));
            }
        }

        computer.clearOutput();
    }

    public void printScreen() {
        PrintCharacter.print(screen);
        System.out.println("Score: " + score);
    }

    Map<Point, Tile> getScreen() {
        return screen;
    }

    public long getBlocks() {
        return screen.values().stream().filter(Tile.BLOCK::equals).count();
    }

    public int getScore() {
        return score;
    }
}
