package utils.classes;

import geometry.Point;

import java.util.Map;

public interface PrintCharacter {
    char getPrintChar();

    default void print() {
        System.out.print(getPrintChar());
    }

    static <T extends PrintCharacter> void print(Map<Point, T> screen) {
        int minX = screen.keySet().stream().mapToInt(key -> key.x).min().orElseThrow();
        int maxX = screen.keySet().stream().mapToInt(key -> key.x).max().orElseThrow();
        int minY = screen.keySet().stream().mapToInt(key -> key.y).min().orElseThrow();
        int maxY = screen.keySet().stream().mapToInt(key -> key.y).max().orElseThrow();

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                screen.get(new Point(x, y)).print();
            }
            System.out.println();
        }
    }
}
