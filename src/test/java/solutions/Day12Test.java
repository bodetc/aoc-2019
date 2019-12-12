package solutions;

import helpers.AsteroidField3D;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    @Test
    void regressionTestFirstStar() {
        AsteroidField3D field = new AsteroidField3D(FileUtils.readLines("day12.txt"));
        field.timesteps(1000);
        int energy = field.energy();
        assertEquals(6423, energy);
    }

    @Test
    void testFindCycleTime() {
        long cycleTime = Day12.findCycleTime(FileUtils.readLines("helpers/asteroid-field/test-input.txt"));
        assertEquals(2772, cycleTime);
    }
}