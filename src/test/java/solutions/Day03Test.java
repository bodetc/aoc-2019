package solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {

    @Test
    void testFindClosestIntersection() {
        assertEquals(Day03.findClosestIntersection("R8,U5,L5,D3", "U7,R6,D4,L4"), 6);
        assertEquals(Day03.findClosestIntersection("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"), 159);
        assertEquals(Day03.findClosestIntersection("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), 135);
    }

    @Test
    void testFindSmallestDelayIntersection() {
        assertEquals(Day03.findSmallestDelayIntersection("R8,U5,L5,D3", "U7,R6,D4,L4"), 30);
        assertEquals(Day03.findSmallestDelayIntersection("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83"), 610);
        assertEquals(Day03.findSmallestDelayIntersection("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), 410);
    }
}