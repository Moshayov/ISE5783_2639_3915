package geometries;
import primitives.*;
public class Plane implements Geometry {
    private Point q0;
    private Vector normal;
    public Plane(Point p0, Point p1, Point p2) throws Exception {
        q0 = p0;
        Vector v1 = p1.Subtract(p1);
        Vector v2 = p0.Subtract(p2);
        normal = v1.crossProduct(v2).normalize();
    }
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
