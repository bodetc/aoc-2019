package solutions.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReactionSolverTest {
    @Test
    void testSolve1() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14/test1.txt"));
        long solution = solver.solveReaction();
        assertEquals(165, solution);
    }

    @Test
    void testSolve2() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14/test2.txt"));
        long solution = solver.solveReaction();
        assertEquals(13312, solution);
    }

    @Test
    void testSolve3() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14/test3.txt"));
        long solution = solver.solveReaction();
        assertEquals(180697, solution);
    }

    @Test
    void testSolve4() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14/test4.txt"));
        long solution = solver.solveReaction();
        assertEquals(2210736, solution);
    }

    @Test
    void testBinarySearch2() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14/test2.txt"));
        long fuel = solver.fuelForOre(1000000000000L);
        assertEquals(82892753, fuel);
    }


    @Test
    void testBinarySearch3() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14/test3.txt"));
        long fuel = solver.fuelForOre(1000000000000L);
        assertEquals(5586022, fuel);
    }


    @Test
    void testBinarySearch4() {
        ReactionSolver solver = new ReactionSolver(Day14.parseInput("day14/test4.txt"));
        long fuel = solver.fuelForOre(1000000000000L);
        assertEquals(460664, fuel);
    }
}