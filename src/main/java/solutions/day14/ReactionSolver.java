package solutions.day14;

import java.util.List;

public class ReactionSolver {
    private static final String FUEL = "FUEL";
    private static final String ORE = "ORE";

    private final List<ChemicalReaction> reactions;

    public ReactionSolver(List<ChemicalReaction> reactions) {
        this.reactions = reactions;
    }

    private long solveReaction(String chemical) {
        if (FUEL.equals(chemical)) {
            return 1;
        }
        return reactions.stream()
                .mapToLong(reaction -> {
                    WeightedName input = reaction.getInput(chemical);
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
}
