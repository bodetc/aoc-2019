package intcode.arcade;

import com.google.common.collect.ImmutableMap;
import geometry.Point;
import intcode.IntcodeComputer;
import utils.classes.PrintCharacter;

import java.util.HashMap;
import java.util.Map;

public class ArcadeCabinet {
    private final IntcodeComputer computer;
    private final Map<Point, Tile> screen = new HashMap<>();

    public ArcadeCabinet(long[] instructions) {
        computer = new IntcodeComputer(instructions);
    }

    public void run() {
        computer.run();
        long[] output = computer.getOutput();

        screen.clear();
        for (int i = 0; i < output.length; i += 3) {
            int x = Math.toIntExact(output[i]);
            int y = Math.toIntExact(output[i + 1]);
            int z = Math.toIntExact(output[i + 2]);
            screen.put(new Point(x, y), Tile.fromValue(z));
        }

        computer.clearOutput();
    }

    public void printScreen() {
        PrintCharacter.print(screen);
    }

    public Map<Point, Tile> getScreen() {
        return ImmutableMap.copyOf(screen);
    }
}
