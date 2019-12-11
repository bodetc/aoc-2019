package solutions;

import utils.FileUtils;

import java.util.Arrays;
import java.util.Comparator;

public class Day08 {

    static int[][][] layerize(int[] input, int width, int height) {
        int layers = input.length / (width * height);
        int[][][] output = new int[layers][height][width];
        for (int l = 0; l < layers; l++) {
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    int index = w + h * width + l * (width * height);
                    output[l][h][w] = input[index];
                }
            }
        }
        return output;
    }

    static int countOf(int[][] input, int value) {
        int count = 0;
        for (int h = 0; h < input.length; h++) {
            for (int w = 0; w < input[h].length; w++) {
                if (input[h][w] == value) {
                    count++;
                }
            }
        }
        return count;
    }

    static int[][] stack(int[][] front, int[][] back) {
        int[][] out = new int[front.length][front[0].length];
        for (int h = 0; h < front.length; h++) {
            for (int w = 0; w < front[h].length; w++) {
                out[h][w] = front[h][w] == 2 // 2 is transparent
                        ? back[h][w]
                        : front[h][w];
            }
        }
        return out;
    }

    static int[][] stack(int[][][] input) {
        int[][] image = input[0];
        for(int i=1; i<input.length; i++) {
            image=stack(image, input[i]);
        }
        return image;
    }

    public static void main(String[] args) {
        int[] inputInts = FileUtils.readInts("day08.txt");
        int[][][] input = layerize(inputInts, 25, 6);

        int[][] fistStarLayer = Arrays.stream(input)
                .min(Comparator.comparingInt(a -> countOf(a, 0)))
                .orElseThrow();

        int firstStartAnswer = countOf(fistStarLayer, 1) * countOf(fistStarLayer, 2);
        System.out.println("Output for first star: " + firstStartAnswer);

        System.out.println("Output for second star: ");
        int[][] image = stack(input);
        for (int h = 0; h < image.length; h++) {
            for (int w = 0; w < image[h].length; w++) {
                System.out.print(image[h][w] == 1 ? '█' : ' ');
            }
            System.out.println();
        }
    }
}
