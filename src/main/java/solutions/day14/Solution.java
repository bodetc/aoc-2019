package solutions.day14;

import com.google.common.annotations.VisibleForTesting;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        List<ChemicalReaction> reactions = parseInput("day14.txt");
        firstStar(reactions);
        secondStar(reactions);
    }

    @VisibleForTesting
    static List<ChemicalReaction> parseInput(String filename) {
        return FileUtils.readLines(filename)
                    .map(ChemicalReaction::new)
                    .collect(Collectors.toList());
    }

    private static void firstStar(List<ChemicalReaction> reactions) {
        ReactionSolver solution = new ReactionSolver(reactions);
        long count = solution.solveReaction();
        System.out.println("Output for first star: " + count);
    }

    private static void secondStar(List<ChemicalReaction> reactions) {
        ReactionSolver solution = new ReactionSolver(reactions);
        long count = solution.solveReaction();
        long amount = 1000000000000L/2210736;
        System.out.println("Output for second star: " + amount);
    }
}
