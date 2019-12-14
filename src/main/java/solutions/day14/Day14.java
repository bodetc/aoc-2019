package solutions.day14;

import com.google.common.annotations.VisibleForTesting;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Day14 {

    public static void main(String[] args) {
        ReactionSolver reactionSolver = new ReactionSolver(parseInput("day14.txt"));
        firstStar(reactionSolver);
        secondStar(reactionSolver);
    }

    @VisibleForTesting
    static List<ChemicalReaction> parseInput(String filename) {
        return FileUtils.readLines(filename)
                .map(ChemicalReaction::new)
                .collect(Collectors.toList());
    }

    private static void firstStar(ReactionSolver reactionSolver) {
        long count = reactionSolver.solveReaction();
        System.out.println("Output for first star: " + count);
    }

    private static void secondStar(ReactionSolver reactionSolver) {
        long amount = reactionSolver.fuelForOre(1000000000000L);
        System.out.println("Output for second star: " + amount);
    }
}
