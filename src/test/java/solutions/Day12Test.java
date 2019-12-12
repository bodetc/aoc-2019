package solutions;

import helpers.AsteroidField;
import helpers.PaintingRobot;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    @Test
    void regressionTestFirstStar() {
        AsteroidField field = new AsteroidField(FileUtils.readLines("day12.txt"));
        field.timesteps(1000);
        int energy = field.energy();
        assertEquals(6423, energy);
    }
}