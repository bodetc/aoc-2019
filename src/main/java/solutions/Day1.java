package solutions;

import utils.FileUtils;

import java.util.function.UnaryOperator;

public class Day1 {

    private static final UnaryOperator<Integer> computeFuel = mass -> mass/3 - 2;

    public static void main(String[] args) {
        int fuel = FileUtils.readResources("day1/input.txt")
                .map(Integer::parseInt)
                .map(computeFuel)
                .reduce(Integer::sum)
                .orElseThrow();
        System.out.println("Total fuel: " + fuel);
    }
}
