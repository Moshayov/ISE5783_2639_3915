package geometries;

/**
 * An abstract class representing a radial geometry in a 3D space.
 */
public abstract class RadialGeometry extends Geometry {
    protected double radius;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     *
     * @param radius the radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("This radius is not legal!");
        this.radius = radius;
    }
}
