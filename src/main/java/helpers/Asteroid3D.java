package helpers;

import com.google.common.annotations.VisibleForTesting;
import geometry.Point;
import geometry.Point3D;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Asteroid3D {
    private Asteroid1D x, y, z;

    Asteroid3D(Point3D position) {
        this(position, Point3D.ORIGIN);
    }

    @VisibleForTesting
    Asteroid3D(Point3D position, Point3D velocity) {
        this.x = new Asteroid1D(position.x, velocity.x);
        this.y = new Asteroid1D(position.y, velocity.y);
        this.z = new Asteroid1D(position.z, velocity.z);
    }

    public void applyGravity(List<Asteroid3D> asteroids) {
        List<Asteroid3D> collect = asteroids.stream()
                .filter(Predicate.not(this::equals))
                .collect(Collectors.toList());
        collect.forEach(asteroid ->
                {
                    x.applyGravity(asteroid.x);
                    y.applyGravity(asteroid.y);
                    z.applyGravity(asteroid.z);
                }
        );
    }

    public void applyVelocity() {
        x.applyVelocity();
        y.applyVelocity();
        z.applyVelocity();
    }

    public int energy() {
        return getPosition().taxiNorm() * getVelocity().taxiNorm();
    }

    Point3D getPosition() {
        return new Point3D(x.position, y.position, z.position);
    }

    Point3D getVelocity() {
        return new Point3D(x.velocity, y.velocity, z.velocity);
    }

    private boolean equals(Asteroid3D other) {
        return getPosition().equals(other.getPosition()) && getVelocity().equals(other.getVelocity());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Asteroid3D && equals((Asteroid3D) other);
    }

    @Override
    public String toString() {
        return "<pos=" + getPosition() + ", vel=" + getVelocity() + ">";
    }
}
