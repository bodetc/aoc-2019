package helpers;

import java.util.List;

public abstract class AsteroidField <T, C extends Asteroid<T, C>>{

    protected final List<C> asteroids;

    protected AsteroidField(List<C> asteroids) {
        this.asteroids = asteroids;
    }

    public void timestep() {
        asteroids.forEach(asteroid -> asteroid.applyGravity(asteroids));
        asteroids.forEach(Asteroid::applyVelocity);
    }

    public void timesteps(int N) {
        for (int i = 0; i < N; i++) {
            timestep();
        }
    }

    public List<C> getAsteroids() {
        return asteroids;
    }
}
