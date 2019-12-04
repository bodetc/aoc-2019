package solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void testIsValid() {
        assertTrue(Day4.isValid(111111));
        assertFalse(Day4.isValid(223450));
        assertFalse(Day4.isValid(123789));
    }
}