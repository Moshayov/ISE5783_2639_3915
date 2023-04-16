package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * A class representing a sphere in a 3D space.
 */
public class Sphere extends RadialGeometry{
   private Point center;
    /**
     * Constructs a new Sphere object with the specified radius.
     *
     * @param radius the radius of the sphere
     */
    public Sphere(double radius) {
        super(radius);
    }
    @Override
    public Vector getNormal(Point p0) throws Exception {
       return null;
    }
}
