package solutions;

import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    @Test
    void testComputeFuel() {
        assertEquals(2, Day01.computeFuel.apply(12));
        assertEquals(2, Day01.computeFuel.apply(14));
        assertEquals(654, Day01.computeFuel.apply(1969));
        assertEquals(33583, Day01.computeFuel.apply(100756));
    }

    @Test
    void testComputeFuelForFuel() {
        assertEquals(2, Day01.computeFuelForFuel.apply(12));
        assertEquals(2, Day01.computeFuelForFuel.apply(14));
        assertEquals(966, Day01.computeFuelForFuel.apply(1969));
        assertEquals(50346, Day01.computeFuelForFuel.apply(100756));
    }

    @Test
    void regressionTestFirstStar() {
        int fuel = FileUtils.readLines("day01.txt")
                .map(Integer::parseInt)
                .map(Day01.computeFuel)
                .reduce(0, Integer::sum);

        assertEquals(3286680, fuel);
    }

    @Test
    void regressionTestSecondStar() {
        int fuel = FileUtils.readLines("day01.txt")
                .map(Integer::parseInt)
                .map(Day01.computeFuelForFuel)
                .reduce(0, Integer::sum);

        assertEquals(4927158, fuel);
    }
}