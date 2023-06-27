package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A class representing a plane in a 3D space.
 */
public class Plane extends Geometry {
    Point q0;
    Vector normal;

    /**
     * Constructs a new Plane object from three points on the plane.
     *
     * @param p0 the first point on the plane
     * @param p1 the second point on the plane
     * @param p2 the third point on the plane
     */
    public Plane(Point p0, Point p1, Point p2) {
        q0 = p0;
        Vector v1 = p0.subtract(p1);
        Vector v2 = p0.subtract(p2);
        normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructs a new Plane object from a point on the plane and its normal vector.
     *
     * @param q0     the point on the plane
     * @param normal the normal vector of the plane
     */
    public Plane(Point q0, Vector normal) {
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

    /**
     * returns the normal vector of the plane
     * @param p0-the point
     * @return normal Vector
     */
    @Override
    public Vector getNormal(Point p0) {
        return normal;
    }

    @Override
    /*
     * Helper method to find the intersections between the given ray and the geometry.
     * The method calculates the intersection point between the ray and the plane defined by the geometry.
     *
     * @param ray the ray for which to find the intersections
     * @return a list of GeoPoints representing the intersections between the ray and the geometry,
     *         or {@code null} if no intersections are found
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        // Check if the starting point of the ray is the same as the point of the plane
        if (q0.equals(P0))
            return null;

        // Check if the ray is parallel to the plane
        Vector q_p = q0.subtract(P0);
        double plane_Point = alignZero(n.dotProdouct(q_p));
        if (isZero(plane_Point))
            return null;

        // Check if the denominator is zero
        double n_v = n.dotProdouct(v);
        if (isZero(n_v))
            return null;

        // Calculate the multiplier of the vector to find the intersection point
        double t = alignZero(plane_Point / n_v);
        if (t <= 0)
            return null;

        List<GeoPoint> points = new ArrayList<>();
        Point intersection_point = ray.getPoint(t);
        points.add(new GeoPoint(this, intersection_point));
        return points;
    }

}
