package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class representing a sphere in a 3D space.
 */
public class Sphere extends RadialGeometry {
    private Point center;

    /**
     * Constructs a new Sphere object with the specified radius.
     * radius is defined in {@link geometries.RadialGeometry#radius}
     */
    public Sphere(Point p, double r) {
        super(r);
        center = p;
    }

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
    public List<Point> findIntersectionPoints(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector u = center.subtract(p0);

        if (p0.equals(center))
            return List.of(center.add(v.scale(radius)));

        double Tm = alignZero(v.dotProdouct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - Tm * Tm));

        if (d >= radius)
            return null;

        double Th = alignZero(radius * radius - d * d);

        double t1 = alignZero(Tm - Th);
        double t2 = alignZero(Tm + Th);

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);
        }

        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            ;
            return List.of(p1);
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);
        }
        return null;
    }
}
