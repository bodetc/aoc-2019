package helpers;

import com.google.common.annotations.VisibleForTesting;

public class Asteroid1D extends Asteroid<Integer, Asteroid1D> {

    public Asteroid1D(int position) {
        this(position, 0);
    }

    @VisibleForTesting
    Asteroid1D(int position, int velocity) {
        super(position, velocity, Asteroid1D.class);
    }

    Asteroid1D(Asteroid1D other) {
        super(other);
    }

    @Override
    public void applyGravity(Asteroid1D asteroid) {
        velocity += Integer.compare(asteroid.position, position);
    }

    @Override
    public void applyVelocity() {
        position += velocity;
    }
}
