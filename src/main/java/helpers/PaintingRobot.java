package helpers;

import geometry.Direction;
import geometry.Point;
import geometry.Vector;
import intcode.IntcodeComputer;
import intcode.IntcodeComputer.ReturnReason;
import intcode.ValueBase;
import utils.HashMapWithDefault;

import java.util.Map;

public class PaintingRobot {
    private enum Color implements ValueBase<Integer> {
        BLACK(0),
        WHITE(1);

        private final int value;

        Color(int value) {
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        public static Color fromValue(int value) {
            return ValueBase.fromValue(values(), value);
        }
    }

    private final IntcodeComputer computer;
    private final Map<Point, Color> hull;
    private Direction direction;
    private Point position;

    public PaintingRobot(long[] instructions) {
        this(instructions, false);
    }

    public PaintingRobot(long[] instructions, boolean startOnWhite) {
        this.computer = new IntcodeComputer(instructions);
        this.hull = new HashMapWithDefault<>(Color.BLACK);

        direction = Direction.U;
        position = Point.ORIGIN;

        if(startOnWhite) {
            hull.put(Point.ORIGIN, Color.WHITE);
        }
    }

    public void run() {
        ReturnReason returnReason = ReturnReason.WAIT_FOR_INPUT;
        while (returnReason == ReturnReason.WAIT_FOR_INPUT) {
            returnReason = runOnce();
        }
    }

    private ReturnReason runOnce() {
        Color currentColor = hull.get(position);
        ReturnReason returnReason = computer.run(currentColor.value);
        long[] output = computer.getOutput();
        // Paint panel
        hull.put(position, Color.fromValue(Math.toIntExact(output[0])));
        // Rotate
        if (output[1] == 0) {
            direction = direction.toLeft();
        } else {
            direction = direction.toRight();
        }
        // Move
        position = position.addVector(new Vector(direction, 1));
        // Clear output
        computer.clearOutput();
        return returnReason;
    }

    public long getPaintedTiles() {
        return hull.size();
    }

    public void printHull() {
        int minX = hull.keySet().stream().mapToInt(key -> key.x).min().orElseThrow();
        int maxX = hull.keySet().stream().mapToInt(key -> key.x).max().orElseThrow();
        int minY = hull.keySet().stream().mapToInt(key -> key.y).min().orElseThrow();
        int maxY = hull.keySet().stream().mapToInt(key -> key.y).max().orElseThrow();

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                System.out.print(hull.get(new Point(x, y)) == Color.WHITE ? '█' : ' ');
            }
            System.out.println();
        }
    }
}
