package geometries;
import primitives.*;
public class Cylinder extends Tube{
    /**
     * Returns the normal vector at the given point on the surface of the cylinder.
     *
     * @param point the point on the surface of the cylinder to calculate the normal vector for
     * @return the normal vector at the given point
     * @throws Exception if the given point is at the center of the base, resulting in a zero vector
     */
    final private double height;
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }
    /**
     * Returns the normal vector at the given point on the surface of the cylinder.
     *
     * @param point the point on the surface of the cylinder to calculate the normal vector for
     * @return the normal vector at the given point
     * @throws Exception if the given point is at the center of the base, resulting in a zero vector
     */
    @Override
    public Vector getNormal(Point point) {
        //  checks if it in the bases center to avoid zero vector
        return null;
    }

}
