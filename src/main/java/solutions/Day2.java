package solutions;

import utils.FileUtils;

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

    private static int runIntcode(int[] input, int noun, int verb) {
        int[] program = input.clone();
        program[1]=noun;
        program[2]=verb;

        int[] output = intcode(program);

        return output[0];
    }

    private static int[] searchIntcode(int[] input, int targetOutput) {
        for(int noun=0; noun<100; noun++) {
            for(int verb=0; verb<100; verb++) {
                int output = runIntcode(input, noun, verb);
                if(output==targetOutput) {
                    return new int[] {noun, verb};
                }
            }
        }
        throw new IllegalStateException("Target output could not be found.");
    }

    public static void main(String[] args) {
        int[] input = FileUtils.readCommaSeparatedValues("day2/input.txt")
                .mapToInt(Integer::parseInt)
                .toArray();

        int value = runIntcode(input, 12, 2);
        System.out.println("Output for first star: " + value);

        int[] result = searchIntcode(input, 19690720);
        int noun=result[0];
        int verb=result[1];
        int answer = noun * 100 + verb;
        System.out.println("noun=" + noun + ", verb=" + verb + ", answer=" + answer);
    }
}

