package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * A class representing a sphere in a 3D space.
 */
public class Sphere extends RadialGeometry{
   private Point center;
    private double radius;
    /**
     * Constructs a new Sphere object with the specified radius.
     *
     * @param radius the radius of the sphere
     */
    public Sphere(Point p, double r) {
        super(r);
        center = p;

    }
    @Override
    public Vector getNormal(Point p0){
        Vector v=p0.Subtract(center);
        return v.normalize();
    }
}
