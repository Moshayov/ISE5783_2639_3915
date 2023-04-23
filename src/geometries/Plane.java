package geometries;
import primitives.*;
/**
 * A class representing a plane in a 3D space.
 */
public class Plane implements Geometry {
    private Point q0;
    private Vector normal;
    /**
     * Constructs a new Plane object from three points on the plane.
     *
     * @param p0 the first point on the plane
     * @param p1 the second point on the plane
     * @param p2 the third point on the plane
     * @throws Exception if the three points are collinear or if an error occurs while calculating the normal vector
     */
    public Plane(Point p0, Point p1, Point p2) throws Exception {
        q0 = p0;
        Vector v1 = p1.Subtract(p1);
        Vector v2 = p0.Subtract(p2);
        normal = v1.crossProduct(v2).normalize();
    }
    /**
     * Constructs a new Plane object from a point on the plane and its normal vector.
     *
     * @param q0 the point on the plane
     * @param normal the normal vector of the plane
     * @throws Exception if an error occurs while normalizing the normal vector
     */
    public Plane(Point q0, Vector normal) throws Exception {
        this.q0 = q0;
        this.normal = normal.normalize();
    }
    /**
     * returns the normal vector of the plane
     *
     * @return normal Vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p0) {
        return normal;
    }

}
