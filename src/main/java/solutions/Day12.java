package solutions;

import com.google.common.collect.ImmutableList;
import geometry.Point3D;
import helpers.AsteroidField1D;
import helpers.AsteroidField3D;
import utils.FileUtils;
import utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {
    private static final List<ToIntFunction<Point3D>> TO_COMPONENTS = ImmutableList.of(
            p -> p.x,
            p -> p.y,
            p -> p.z
    );

    public static long findCycleTime(Stream<String> input) {
        List<Point3D> points = input
                .map(AsteroidField3D::parseCoordinates)
                .collect(Collectors.toList());

        List<Long> cycles = new ArrayList<>();
        for (ToIntFunction<Point3D> toComponent : TO_COMPONENTS) {
            int[] coordinates = points.stream()
                    .mapToInt(toComponent)
                    .toArray();

            AsteroidField1D field = new AsteroidField1D(coordinates);
            long cycle = field.runUntilLoop();

            System.out.println(cycle);
            cycles.add(cycle);
        }

        return MathUtils.lcm(cycles);
    }

    public static void main(String[] args) {
        AsteroidField3D field = new AsteroidField3D(FileUtils.readLines("day12.txt"));
        field.timesteps(1000);
        int energy = field.energy();
        System.out.println("Output for first star: " + energy);

        long time = findCycleTime(FileUtils.readLines("day12.txt"));
        System.out.println("Output for second star: " + time);
    }
}
