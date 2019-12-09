package solutions;

import intcode.EarlyIntcodeComputer;
import utils.FileUtils;

public class Day2 {

    static int[] searchIntcode(int[] input, int targetOutput) {
        for(int noun=0; noun<100; noun++) {
            for(int verb=0; verb<100; verb++) {
                int output = EarlyIntcodeComputer.runNounVerb(input, noun, verb);
                if(output==targetOutput) {
                    return new int[] {noun, verb};
                }
            }
        }
        throw new IllegalStateException("Target output could not be found.");
    }

    public static void main(String[] args) {
        int[] input = FileUtils.readCommaSeparatedInts("day2.txt");

        int value = EarlyIntcodeComputer.runNounVerb(input,12, 2);
        System.out.println("Output for first star: " + value);

        int[] result = searchIntcode(input, 19690720);
        int noun=result[0];
        int verb=result[1];
        int answer = noun * 100 + verb;
        System.out.println("noun=" + noun + ", verb=" + verb + ", answer=" + answer);
    }
}

