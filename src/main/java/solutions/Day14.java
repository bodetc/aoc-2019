package solutions;

import utils.FileUtils;
import utils.Reject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day14 {

    private static final String FUEL = "FUEL";
    private static final String ORE = "ORE";

    private static class WeightedName {
        private final int amount;

        private final String name;

        private WeightedName(String input) {
            String[] split = input.split(" ");
            Reject.ifFalse(split.length == 2);
            this.amount = Integer.parseInt(split[0]);
            this.name = split[1];
        }

        public String getName() {
            return name;
        }
    }

    private static class ChemicalReaction {
        private final Map<String, WeightedName> input;
        private final WeightedName output;

        private ChemicalReaction(String line) {
            String[] split = line.split(" => ");
            Reject.ifFalse(split.length == 2);
            this.input = parseInput(split[0]);
            this.output = new WeightedName(split[1]);
        }

        private String getOutputName() {
            return output.name;
        }

        private static Map<String, WeightedName> parseInput(String line) {
            String[] split = line.split(", ");
            return Arrays.stream(split)
                    .map(WeightedName::new)
                    .collect(Collectors.toMap(WeightedName::getName,
                            Function.identity()));
        }
    }

    private final List<ChemicalReaction> reactions;

    public Day14(Stream<String> input) {
        this.reactions = input
                .map(ChemicalReaction::new)
                .collect(Collectors.toList());
    }

    private long solveReaction(String chemical) {
        if (FUEL.equals(chemical)) {
            return 1;
        }
        return reactions.stream()
                .mapToLong(reaction -> {
                    WeightedName input = reaction.input.get(chemical);
                    if (input == null) {
                        return 0;
                    } else {
                        return input.amount
                                * (long) Math.ceil((double) solveReaction(reaction.output.name) / (double) reaction.output.amount);
                    }
                })
                .sum();
    }

    public long solveReaction() {
        return solveReaction(ORE);
    }

    public static void main(String[] args) {
        firstStar();
    }

    private static void firstStar() {
        Day14 day14 = new Day14(FileUtils.readLines("day14.txt"));
        long count = day14.solveReaction();
        System.out.println("Output for first star: " + count);
    }
}
