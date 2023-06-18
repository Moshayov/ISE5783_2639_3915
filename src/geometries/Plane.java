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
    private final Point q0;
    private final Vector normal;

    /**
     * Constructs a new Plane object from three points on the plane.
     *
     * @param p0 the first point on the plane
     * @param p1 the second point on the plane
     * @param p2 the third point on the plane
     */
    public Plane(Point p0, Point p1, Point p2) {
        q0 = p0;
        Vector v1 = q0.subtract(p1);
        Vector v2 = q0.subtract(p2);
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

    @Override
    public Vector getNormal(Point p0) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        /**
         * If the point of the plane to the starting point of
         *the ray is not considered as intersecting we will return nul
         */
        if (q0.equals(P0))
            return null;
        /**
         * If the number of points is 0, it means that there are no intersection points
         * and we will return null
         */
        Vector q_p = q0.subtract(P0);
        double plane_Point = alignZero(n.dotProdouct(q_p));
        if (isZero(plane_Point))
            return null;
        /*If the denominator is equal to zero we will not
         *be able to find T because it is impossible to divide by zero
         *and therefore we will return null
         */
        double n_v = n.dotProdouct(v);
        if (isZero(n_v))
            return null;
        /*Calculating the multiplier of the vector to find the intersection point.
         *if it is less than or equal to zero, that means there is none and we will return NULL.
         *Otherwise we will find the point and return the list of intersection points
         */
        double t = alignZero(plane_Point / n_v);
        if (t <= 0)
            return null;
        List<GeoPoint> points = new ArrayList<>();
        Point intersection_point = ray.getPoint(t);
        points.add(new GeoPoint(this, intersection_point));
        return points;

    }
}
