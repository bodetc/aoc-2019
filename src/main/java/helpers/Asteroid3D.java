package helpers;

import com.google.common.annotations.VisibleForTesting;
import geometry.Point;
import geometry.Point3D;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Asteroid3D {
    private Point3D position;
    private Point3D velocity;

    Asteroid3D(Point3D position) {
        this(position, Point3D.ORIGIN);
    }

    @VisibleForTesting
    Asteroid3D(Point3D position, Point3D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void applyGravity(List<Asteroid3D> asteroids) {
        List<Asteroid3D> collect = asteroids.stream()
                .filter(Predicate.not(this::equals))
                .collect(Collectors.toList());
        collect.forEach(asteroid ->
                velocity = velocity.add(gravityChange(position.x, asteroid.position.x),
                        gravityChange(position.y, asteroid.position.y),
                        gravityChange(position.z, asteroid.position.z)
                ));
    }

    public void applyVelocity() {
        position = position.add(velocity.x, velocity.y, velocity.z);
    }

    private static int gravityChange(int position, int other) {
        return Integer.compare(other, position);
    }

    private boolean equals(Asteroid3D other) {
        return position.equals(other.position) && velocity.equals(other.velocity);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Asteroid3D && equals((Asteroid3D) other);
    }

    @Override
    public String toString() {
        return "<pos=" + position + ", vel=" + velocity + ">";
    }
}
