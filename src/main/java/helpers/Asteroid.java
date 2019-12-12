package helpers;

import com.google.common.annotations.VisibleForTesting;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Asteroid<T, C extends Asteroid<T, C>> {
    public T position;
    protected T velocity;
    final Class<C> type;

    @VisibleForTesting
    Asteroid(T position, T velocity, Class<C> type) {
        this.position = position;
        this.velocity = velocity;
        this.type = type;
    }

    Asteroid(C other) {
        this(other.position, other.velocity, other.type);
    }

    public void applyGravity(List<C> asteroids) {
        asteroids.stream()
                .filter(Predicate.not(this::equals))
                .collect(Collectors.toList())
                .forEach(this::applyGravity);
    }

    abstract public void applyGravity(C asteroid);

    abstract public void applyVelocity();

    private boolean equals(C other) {
        return position.equals(other.position) && velocity.equals(other.velocity);
    }

    @Override
    public boolean equals(Object other) {
        return type.isInstance(other) && equals(type.cast(other));
    }

    @Override
    public String toString() {
        return "<pos=" + position + ", vel=" + velocity + ">";
    }
}
