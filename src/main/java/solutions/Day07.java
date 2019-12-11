package solutions;

import intcode.IntcodeComputer;
import intcode.IntcodeComputer.ReturnReason;
import utils.FileUtils;
import utils.MathUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day07 {
    private static class Amplifier {
        private final IntcodeComputer computer;


        private Amplifier(long[] instructions, int phase) {
            this.computer = new IntcodeComputer(instructions);
            computer.run(phase);
        }

        private ReturnReason run(long input) {
            return computer.run(input);
        }

        private long getLastOutput(){
            return computer.getLastOutput();
        }
    }

    private static long runAmplifiers(long[] program, int[] phases) {
        Amplifier[] amplifiers = Arrays.stream(phases)
                .mapToObj(phase -> new Amplifier(program, phase))
                .toArray(Amplifier[]::new);

        long output = 0;
        ReturnReason returnReason = ReturnReason.WAIT_FOR_INPUT;

        while (returnReason==ReturnReason.WAIT_FOR_INPUT) {
            for (Amplifier amplifier : amplifiers) {
                returnReason = amplifier.run(output);
                output = amplifier.getLastOutput();
            }
        }
        return output;
    }

    static long searchPhases(long[] program, int start, int end) {
        int[] phasesValues = IntStream.range(start, end).toArray();
        List<int[]> permutations = MathUtils.permutations(phasesValues);

        return permutations.stream()
                .map(phases -> runAmplifiers(program, phases))
                .max(Comparator.comparingLong(Long::longValue))
                .orElseThrow();
    }

    public static void main(String[] args) {
        long[] program = FileUtils.readCommaSeparatedLongs("day07.txt");

        long maxThrusterSignal = searchPhases(program, 0,5);
        System.out.println("Output for first star: " + maxThrusterSignal);

        long maxSignal = searchPhases(program, 5,10);
        System.out.println("Output for second star: " + maxSignal);
    }
}
