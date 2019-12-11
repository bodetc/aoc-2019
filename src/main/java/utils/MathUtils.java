package utils;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {

    public static boolean isBetween(int a, int b, int c) {
        if (b < a) {
            return isBetween(b, a, c);
        }
        return a <= c && c <= b;
    }

    public static List<int[]> permutations(int[] input) {
        int n = input.length;
        List<int[]> output = new ArrayList<>();

        int[] elements = input.clone();
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        output.add(elements.clone());

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                output.add(elements.clone());
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }

        return output;
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    public static long nullToZero(Long value) {
        return value == null ? 0 : value;
    }

    public static double correctAngle(double angle) {
        if(angle<0) {
            return angle + 2.*Math.PI;
        } else if(angle>2.*Math.PI) {
            return angle - 2.*Math.PI;
        } else {
            return angle;
        }
    }
}
