package solutions;

import intcode.arcade.ArcadeCabinet;
import intcode.arcade.ArtificialIntelligence;
import intcode.arcade.Joystick;
import utils.FileUtils;

public class Day13 {

    public static void main(String[] args) {
        firstStar();
        secondStar();
    }

    private static void firstStar() {
        long[] instructions = FileUtils.readCommaSeparatedLongs("day13.txt");
        ArcadeCabinet cabinet = new ArcadeCabinet(instructions);
        cabinet.run();
        cabinet.printScreen();
        long count = cabinet.getBlocks();
        System.out.println("Output for first star: " + count);
    }

    private static void secondStar() {
        long[] instructions = FileUtils.readCommaSeparatedLongs("day13.txt");
        instructions[0] = 2; // Insert COINS
        ArcadeCabinet cabinet = new ArcadeCabinet(instructions);
        ArtificialIntelligence ai = new ArtificialIntelligence(cabinet);
        Joystick joystick = Joystick.NEUTRAL;

        do {
            cabinet.run(joystick);
            cabinet.printScreen();
            joystick = ai.nextMove();
        } while (cabinet.getBlocks() > 0);

        int score = cabinet.getScore();
        System.out.println("Output for first star: " + score);
    }
}
