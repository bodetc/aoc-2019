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

    @Test
    void testIsExactlyValid() {
        assertTrue(Day4.isExactlyValid(112233));
        assertFalse(Day4.isExactlyValid(123444));
        assertTrue(Day4.isExactlyValid(111122));
    }
}