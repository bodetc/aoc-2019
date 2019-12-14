package solutions.day14;

import org.junit.jupiter.api.Test;
import solutions.day14.Solution;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    @Test
    void regressionTestFirstStar() {
        ReactionSolver solver = new ReactionSolver(Solution.parseInput("day14.txt"));
        long solution = solver.solveReaction();
        assertEquals(301997, solution);
    }

    @Test
    void testSolve1() {
        ReactionSolver solver = new ReactionSolver(Solution.parseInput("day14/test1.txt"));
        long solution = solver.solveReaction();
        assertEquals(165, solution);
    }

    @Test
    void testSolve2() {
        ReactionSolver solver = new ReactionSolver(Solution.parseInput("day14/test2.txt"));
        long solution = solver.solveReaction();
        assertEquals(13312, solution);
    }

    @Test
    void testSolve3() {
        ReactionSolver solver = new ReactionSolver(Solution.parseInput("day14/test3.txt"));
        long solution = solver.solveReaction();
        assertEquals(180697, solution);
    }

    @Test
    void testSolve4() {
        ReactionSolver solver = new ReactionSolver(Solution.parseInput("day14/test4.txt"));
        long solution = solver.solveReaction();
        assertEquals(2210736, solution);
    }

}