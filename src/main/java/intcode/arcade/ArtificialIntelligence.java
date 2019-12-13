package intcode.arcade;

import geometry.Point;

import java.util.Map;

public class ArtificialIntelligence {
    private final ArcadeCabinet cabinet;

    public ArtificialIntelligence(ArcadeCabinet cabinet) {
        this.cabinet = cabinet;
    }

    public Joystick nextMove() {
        Point ball = getPosition(Tile.BALL);
        Point paddle = getPosition(Tile.PADDLE);
        return Joystick.fromValue(Integer.compare(ball.x, paddle.x));
    }

    private Point getPosition(Tile tile) {
        return cabinet.getScreen().entrySet().stream()
                .filter(entry -> tile.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow();
    }
}
