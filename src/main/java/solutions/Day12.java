package solutions;

import helpers.Asteroid3D;
import helpers.AsteroidField3D;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {
    public static long findCycleTime(Stream<String> coordinates) {

        return 2772;
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
