package solutions;

import helpers.AsteroidField;
import utils.FileUtils;

public class Day12 {
    public static void main(String[] args) {
        AsteroidField field = new AsteroidField(FileUtils.readLines("day12.txt"));
        field.timesteps(1000);
        int energy = field.energy();
        System.out.println("Output for first star: " + energy);
    }
}
