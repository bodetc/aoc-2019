package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class AsteroidField<T, C extends Asteroid<T, C>> {

    private final List<C> asteroids;
    private long time = 0;

    AsteroidField(List<C> asteroids) {
        this.asteroids = new ArrayList<>(asteroids);
    }

    public void timestep() {
        asteroids.forEach(asteroid -> asteroid.applyGravity(asteroids));
        asteroids.forEach(Asteroid::applyVelocity);
        time++;
    }

    public void timesteps(int N) {
        for (int i = 0; i < N; i++) {
            timestep();
        }
    }

    public Stream<C> streamAsteroids() {
        return asteroids.stream();
    }

    public long getTime() {
        return time;
    }
}
