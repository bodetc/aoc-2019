package solutions;

import utils.FileUtils;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Day1 {

    static final UnaryOperator<Integer> computeFuel = mass -> mass / 3 - 2;
    private static final Predicate<Integer> isMassPositive = mass -> mass > 0;
    static final UnaryOperator<Integer> computeFuelForFuel = moduleMass ->
            Stream.iterate(moduleMass, isMassPositive, computeFuel)
                    .filter(mass -> !Objects.equals(mass, moduleMass)) // Only fuel mass is asked, not launch mass
                    .reduce(0, Integer::sum);

    public static void main(String[] args) {
        int fuel = FileUtils.readLines("day1.txt")
                .map(Integer::parseInt)
                .map(computeFuel)
                .reduce(0, Integer::sum);

        System.out.println("Total fuel: " + fuel);

        int fuelWithFuelForFuel = FileUtils.readLines("day1.txt")
                .map(Integer::parseInt)
                .map(computeFuelForFuel)
                .reduce(0, Integer::sum);

        System.out.println("Total fuel with fuel for fuel: " + fuelWithFuelForFuel);
    }
}
