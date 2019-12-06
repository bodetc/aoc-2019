package solutions;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void regressionTestFirstStar() {
        assertEquals(454, Day4.valid(IntStream.range(402328, 864247)).count());
    }

    @Test
    void regressionTestSecondStar() {
        assertEquals(288, Day4.exactlyValid(IntStream.range(402328, 864247)).count());

    }

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