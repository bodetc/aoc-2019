package solutions.day14;

import utils.Reject;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class ChemicalReaction {
    private final Map<String, WeightedName> input;
    final WeightedName output;

    ChemicalReaction(String line) {
        String[] split = line.split(" => ");
        Reject.ifFalse(split.length == 2);
        this.input = parseInput(split[0]);
        this.output = new WeightedName(split[1]);
    }

    private static Map<String, WeightedName> parseInput(String line) {
        String[] split = line.split(", ");
        return Arrays.stream(split)
                .map(WeightedName::new)
                .collect(Collectors.toMap(w -> w.name,
                        Function.identity()));
    }

    WeightedName getInput(String name) {
        return input.get(name);
    }
}
