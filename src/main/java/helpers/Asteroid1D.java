package helpers;

import com.google.common.annotations.VisibleForTesting;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Asteroid1D {
    int position;
    int velocity;

    Asteroid1D(int position) {
        this(position, 0);
    }

    @VisibleForTesting
    Asteroid1D(int position, int velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    Asteroid1D(Asteroid1D other) {
        this(other.position, other.velocity);
    }

    public void applyGravity(List<Asteroid1D> asteroids) {
        asteroids.stream()
                .filter(Predicate.not(this::equals))
                .collect(Collectors.toList())
                .forEach(this::applyGravity);
    }

    void applyGravity(Asteroid1D asteroid) {
        velocity += Integer.compare(asteroid.position, position);
    }

    public void applyVelocity() {
        position += velocity;
    }

    private boolean equals(Asteroid1D other) {
        return position == other.position && velocity == other.velocity;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Asteroid1D && equals((Asteroid1D) other);
    }

    @Override
    public String toString() {
        return "<pos=" + position + ", vel=" + velocity + ">";
    }
}
