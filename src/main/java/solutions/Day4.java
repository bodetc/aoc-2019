package solutions;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Day4 {

    private static int[] toDigits(int number) {
        List<Integer> digits = new ArrayList<>(6);
        while (number > 0) {
            digits.add(number % 10);
            number = number / 10;
        }
        Collections.reverse(digits);
        return Ints.toArray(digits);
    }

    private static int toNumber(int[] digits) {
        int number = 0;
        for (int digit : digits) {
            number = number * 10 + digit;
        }
        return number;
    }


    private static boolean isNdigits(int[] digits, int N) {
        return digits.length == N;
    }

    private static boolean is6digits(int[] digits) {
        return isNdigits(digits, 6);
    }

    private static boolean hasTwoSameAdjacentDigits(int[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] == digits[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasExactlyTwoSameAdjacentDigits(int[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            int digit = digits[i];
            if ((digit == digits[i + 1])
                    && (i == digits.length - 2 || digits[i + 2] != digit)
                    && (i == 0 || digits[i - 1] != digit)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMonotonouslyIncreasing(int[] digits) {
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] > digits[i + 1]) {
                return false;
            }
        }
        return true;
    }

    @VisibleForTesting
    static IntStream valid(IntStream input) {
        return input
                .mapToObj(Day4::toDigits)
                .filter(Day4::is6digits)
                .filter(Day4::hasTwoSameAdjacentDigits)
                .filter(Day4::isMonotonouslyIncreasing)
                .mapToInt(Day4::toNumber);
    }

    @VisibleForTesting
    static IntStream exactlyValid(IntStream input) {
        return input
                .mapToObj(Day4::toDigits)
                .filter(Day4::is6digits)
                .filter(Day4::hasExactlyTwoSameAdjacentDigits)
                .filter(Day4::isMonotonouslyIncreasing)
                .mapToInt(Day4::toNumber);
    }

    static boolean isValid(int number) {
        return valid(IntStream.of(number)).count() == 1;
    }

    static boolean isExactlyValid(int number) {
        return exactlyValid(IntStream.of(number)).count() == 1;
    }

    public static void main(String[] args) {
        IntStream validNumbers = valid(IntStream.range(402328, 864247));
        System.out.println("Number of valid passwords: " + validNumbers.count());

        IntStream exactlyValidNumbers = exactlyValid(IntStream.range(402328, 864247));
        System.out.println("Number of exactly valid passwords: " + exactlyValidNumbers.count());
    }
}
