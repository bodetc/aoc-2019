package solutions;

import intcode.IntcodeComputer;
import utils.MathUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day7 {
    private static class Amplifier {
        private final IntcodeComputer computer;
        private final int phase;


        private Amplifier(int[] program, int phase) {
            this.computer = new IntcodeComputer(program);
            this.phase = phase;
        }

        private int run(int input) {
            int[] output = computer.runIO(new int[]{phase, input});
            return output[0];
        }
    }

    private static class Result {
        private final int[] phases;
        private final int output;

        private Result(int[] phases, int output) {
            this.phases = phases;
            this.output = output;
        }
    }

    private static Result runAmplifiers(int[] program, int[] phases) {
        int output = 0;
        for (int phase : phases) {
            Amplifier amplifier = new Amplifier(program, phase);
            output = amplifier.run(output);
        }
        return new Result(phases, output);
    }

    static int searchPhases(int[] program, int nAmplifiers) {
        int[] phasesValues = IntStream.range(0, nAmplifiers).toArray();
        List<int[]> permutations = MathUtils.permutations(phasesValues);
        Result result = permutations.stream()
                .map(phases -> runAmplifiers(program, phases))
                .max(Comparator.comparingInt(a -> a.output))
                .orElseThrow();

        return result.output;
    }
}
