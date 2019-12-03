package solutions;

import utils.FileUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {

    public static int[] intcode(int[] input) {
        for (int pos = 0; pos < input.length; pos += 4) {
            int opcode = input[pos];

            if (opcode == 99) {
                return input;
            }

            int pos1 = input[pos + 1];
            int pos2 = input[pos + 2];
            int posR = input[pos + 3];

            if (opcode == 1) {
                input[posR] = input[pos1] + input[pos2];
            } else if (opcode == 2) {
                input[posR] = input[pos1] * input[pos2];
            } else {
                throw new IllegalStateException("Encountering an unknown opcode means something went wrong.");
            }
        }
        throw new IllegalStateException("Intcode program should end when encountering 99.");
    }

    public static void main(String[] args) {
        int[] input = FileUtils.readCommaSeparatedValues("day2/input.txt")
                .mapToInt(Integer::parseInt)
                .toArray();

        // Change program as instructed
        input[1]=12;
        input[2]=2;

        int[] output = intcode(input);

        String outputString = Arrays.stream(output)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        System.out.println("Intcode output: " + outputString);

        System.out.println("Value is left at position 0: " + output[0]);
    }
}

