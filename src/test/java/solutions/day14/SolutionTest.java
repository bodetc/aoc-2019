package solutions.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    @Test
    void regressionTestFirstStar() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14.txt"));
        long solution = solver.solveReaction();
        assertEquals(301997, solution);
    }

    @Test
    void regressionTestSecondStar() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14.txt"));
        long solution = solver.fuelForOre(1000000000000L);
        assertEquals(6216589, solution);
    }
}