package solutions;

import intcode.arcade.ArcadeCabinet;
import intcode.arcade.Tile;
import utils.FileUtils;

public class Day13 {

    public static void main(String[] args) {
        ArcadeCabinet cabinet = new ArcadeCabinet(FileUtils.readCommaSeparatedLongs("day13.txt"));
        cabinet.run();
        cabinet.printScreen();
        long count = cabinet.getScreen().values().stream().filter(Tile.BLOCK::equals).count();
        System.out.println("Output for first star: " + count);

        //long time = findCycleTime(FileUtils.readLines("day12.txt"));
        //System.out.println("Output for second star: " + time);
    }
}
