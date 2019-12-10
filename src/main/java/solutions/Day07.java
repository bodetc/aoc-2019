package solutions;

import intcode.IntcodeComputer;
import utils.FileUtils;
import utils.MathUtils;

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

        private long run(long input) {
            computer.run(input);
            return computer.getLastOutput();
        }
    }

    private static class Result {
        private final int[] phases;
        private final long output;

        private Result(int[] phases, long output) {
            this.phases = phases;
            this.output = output;
        }
    }

    private static Result runAmplifiers(long[] program, int[] phases) {
        long output = 0;
        for (int phase : phases) {
            Amplifier amplifier = new Amplifier(program, phase);
            output = amplifier.run(output);
        }
        return new Result(phases, output);
    }

    static long searchPhases(long[] program, int nAmplifiers) {
        int[] phasesValues = IntStream.range(0, nAmplifiers).toArray();
        List<int[]> permutations = MathUtils.permutations(phasesValues);
        Result result = permutations.stream()
                .map(phases -> runAmplifiers(program, phases))
                .max(Comparator.comparingLong(a -> a.output))
                .orElseThrow();

        return result.output;
    }

    public static void main(String[] args) {
        long[] program = FileUtils.readCommaSeparatedLongs("day07.txt");

        long maxThrusterSignal = searchPhases(program, 5);
        System.out.println("Output for first star: " + maxThrusterSignal);
    }
}
