package solutions;

import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {
    @Test
    void regressionTestFirstStar() {
        Day14 day14 = new Day14(FileUtils.readLines("day14.txt"));
        long solution = day14.solveReaction();
        assertEquals(301997, solution);
    }

    @Test
    void testSolve1() {
        Day14 day14 = new Day14(FileUtils.readLines("day14/test1.txt"));
        long solution = day14.solveReaction();
        assertEquals(165, solution);
    }

    @Test
    void testSolve2() {
        Day14 day14 = new Day14(FileUtils.readLines("day14/test2.txt"));
        long solution = day14.solveReaction();
        assertEquals(13312, solution);
    }

    @Test
    void testSolve3() {
        Day14 day14 = new Day14(FileUtils.readLines("day14/test3.txt"));
        long solution = day14.solveReaction();
        assertEquals(180697, solution);
    }

    @Test
    void testSolve4() {
        Day14 day14 = new Day14(FileUtils.readLines("day14/test4.txt"));
        long solution = day14.solveReaction();
        assertEquals(2210736, solution);
    }

}