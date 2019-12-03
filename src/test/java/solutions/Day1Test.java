package solutions;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @org.junit.jupiter.api.Test
    void testComputeFuel() {
        assertEquals(Day1.computeFuel.apply(12), 2);
        assertEquals(Day1.computeFuel.apply(14), 2);
        assertEquals(Day1.computeFuel.apply(1969), 654);
        assertEquals(Day1.computeFuel.apply(100756), 33583);
    }

    @org.junit.jupiter.api.Test
    void testComputeFuelForFuel() {
        assertEquals(Day1.computeFuelForFuel.apply(12), 2);
        assertEquals(Day1.computeFuelForFuel.apply(14), 2);
        assertEquals(Day1.computeFuelForFuel.apply(1969), 966);
        assertEquals(Day1.computeFuelForFuel.apply(100756), 50346);
    }
}