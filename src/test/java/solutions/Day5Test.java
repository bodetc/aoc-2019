package solutions;

import intcode.EarlyIntcodeComputer;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void regressionTestFirstStar() {
        long[] program = FileUtils.readCommaSeparatedLongs("day5.txt");
        assertEquals(13787043, EarlyIntcodeComputer.runIO(program,1));
    }

    @Test
    void regressionTestSecondStar() {
        long[] program = FileUtils.readCommaSeparatedLongs("day5.txt");
        assertEquals(3892695, EarlyIntcodeComputer.runIO(program,5));
    }
}