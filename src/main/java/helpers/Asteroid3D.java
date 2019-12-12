package helpers;

import geometry.Point3D;

public class Asteroid3D extends Asteroid<Point3D, Asteroid3D> {
    Asteroid3D(Point3D position) {
        this(position, Point3D.ORIGIN);
    }

    public Asteroid3D(Point3D position, Point3D velocity) {
        super(position, velocity, Asteroid3D.class);
    }

    @Override
    public void applyGravity(Asteroid3D asteroid) {
        velocity = velocity.add(
                gravityChange(position.x, asteroid.position.x),
                gravityChange(position.y, asteroid.position.y),
                gravityChange(position.z, asteroid.position.z));
    }

    @Override
    public void applyVelocity() {
        position = position.add(velocity.x, velocity.y, velocity.z);
    }

    private static int gravityChange(int position, int other) {
        return Integer.compare(other, position);
    }

    public int energy() {
        return position.taxiNorm() * velocity.taxiNorm();
    }
}
