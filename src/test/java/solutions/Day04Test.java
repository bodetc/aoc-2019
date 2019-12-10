package solutions;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

    @Test
    void regressionTestFirstStar() {
        assertEquals(454, Day04.valid(IntStream.range(402328, 864247)).count());
    }

    @Test
    void regressionTestSecondStar() {
        assertEquals(288, Day04.exactlyValid(IntStream.range(402328, 864247)).count());

    }

    @Test
    void testIsValid() {
        assertTrue(Day04.isValid(111111));
        assertFalse(Day04.isValid(223450));
        assertFalse(Day04.isValid(123789));
    }

    @Test
    void testIsExactlyValid() {
        assertTrue(Day04.isExactlyValid(112233));
        assertFalse(Day04.isExactlyValid(123444));
        assertTrue(Day04.isExactlyValid(111122));
    }
}