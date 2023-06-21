package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class representing a sphere in a 3D space.
 */
public class Sphere extends RadialGeometry {
   // private final Point center;
   Point center;

    /**
     * Constructs a new Sphere object with the specified radius.
     * radius is defined in {@link geometries.RadialGeometry#radius}
     */
    public Sphere(Point p, double r) {
        super(r);
        center = p;
    }

    /**
     * Returns the normal vector to the geometry at the specified point.
     * The normal vector is calculated as the normalized vector from the center of the geometry to the specified point.
     *
     * @param p0 the point on the geometry for which to calculate the normal vector
     * @return the normal vector to the geometry at the specified point
     */
    @Override
    public Vector getNormal(Point p0) {
        Vector v = p0.subtract(center);
        return v.normalize();
    }


    /**
     * Finds the intersection points between this sphere and a given ray.
     *
     * @param ray the ray to intersect with this sphere
     * @return a list of intersection points, or null if the ray does not intersect
     * the sphere
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (ray.getP0().equals(center)) {
            List<GeoPoint> points = new ArrayList<>(1);
            Point p = center.add(ray.getDir().scale(radius));
            points.add(new GeoPoint(this, p));
            return points;
        }

        Vector u = center.subtract(ray.getP0());
        double Tm = ray.getDir().dotProdouct(u);
        double d = Math.sqrt(u.lengthSquared()-Tm*Tm);
        if (d >= radius)
            return null;
        double Th = Math.sqrt(radius*radius-d*d);
        double t1 = alignZero(Tm + Th);
        double t2 = alignZero(Tm - Th);
        if (t1 <= 0 && t2 <= 0)
            return null;
        int size = 0;
        if(t1 > 0)
            size += 1;
        if(t2 > 0)
            size += 1;
        List<GeoPoint> points = new ArrayList<>(size);
        if (t1 > 0) {
            Point p = ray.getPoint(t1);
            points.add(new GeoPoint(this, p));
        }
        if (t2 > 0) {
            Point p = ray.getPoint(t2);
            points.add(new GeoPoint(this, p));
        }
        return points;
    }
}
