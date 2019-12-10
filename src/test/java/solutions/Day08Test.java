package solutions;

import org.junit.jupiter.api.Test;
import utils.FileUtils;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    @Test
    void regressionTestFirstStar() {
        int[] inputInts = FileUtils.readInts("day08.txt");
        int[][][] input = Day08.layerize(inputInts, 25, 6);

        int[][] fistStarLayer = Arrays.stream(input)
                .min(Comparator.comparingInt(a -> Day08.countOf(a, 0)))
                .orElseThrow();

        int firstStartAnswer = Day08.countOf(fistStarLayer, 1) * Day08.countOf(fistStarLayer, 2);
        assertEquals(2159, firstStartAnswer);
    }

    @Test
    void testLayerize() {
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
        int[][][] output = Day08.layerize(input, 3, 2);
        int[][][] expected = new int[][][]{
                {
                        {1, 2, 3},
                        {4, 5, 6}
                },
                {
                        {7, 8, 9},
                        {0, 1, 2}
                }
        };
        for (int l = 0; l < expected.length; l++) {
            for (int h = 0; h < expected[l].length; h++) {
                for (int w = 0; w < expected[l][h].length; w++) {
                    assertEquals(expected[l][h][w], output[l][h][w]);
                }
            }
        }
    }

    @Test
    void testStack() {
        int[] input = new int[]{0, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 2, 0, 0, 0, 0};
        int[][][] layers = Day08.layerize(input, 2, 2);
        int[][] image = Day08.stack(layers);

        int[][] expected = new int[][]{
                {0, 1},
                {1, 0}
        };

        for (int h = 0; h < expected.length; h++) {
            for (int w = 0; w < expected[h].length; w++) {
                assertEquals(expected[h][w], image[h][w]);
            }
        }
    }
}