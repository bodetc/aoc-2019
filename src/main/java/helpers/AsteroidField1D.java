package helpers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AsteroidField1D extends AsteroidField<Integer, Asteroid1D> {

    private final int[] startingCoordinates;

    public AsteroidField1D(int[] coordinates) {
        super(Arrays.stream(coordinates)
                .mapToObj(Asteroid1D::new)
                .collect(Collectors.toList())
        );
        this.startingCoordinates = coordinates;
    }

    public long runUntilLoop() {
        do {
            timestep();
        } while (isDifferent());

        return getTime();
    }

    private boolean isDifferent() {
        Asteroid1D[] asteroids = streamAsteroids().toArray(Asteroid1D[]::new);
        for (int i = 0; i < startingCoordinates.length; i++) {
            Asteroid1D asteroid = asteroids[i];
            if (!asteroid.velocity.equals(0) || !asteroid.position.equals(startingCoordinates[i])) {
                return true;
            }
        }
        return false;
    }
}
