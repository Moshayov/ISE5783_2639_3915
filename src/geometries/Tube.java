/**
 * The geometries package provides classes for representing different types of geometries in 3D space.
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Tube class represents a tube geometry in 3D space.
 * A tube is defined by an axis ray and a radius.
 */
public class Tube extends RadialGeometry {
    //protected Ray axisRay;
    Ray axisRay;

    /**
     * Constructs a new Tube object with the specified axis ray and radius.
     *
     * @param axisRay the axis ray of the tube
     * @param radius  the radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Calculates the normal vector at a given point on the surface of the tube.
     *
     * @param point the point on the surface of the tube
     * @return the normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector p0_p = point.subtract(p0);
        double t = alignZero(p0_p.dotProdouct(v));

        if (isZero(t)) {
            return p0_p.normalize();
        }
        Point O = axisRay.getPoint(t);
        Vector O_P = point.subtract(O);

        return O_P.normalize();
    }

    /**
     * Helper method to find the intersections between the given ray and the tube geometry.
     *
     * @param ray the ray for which to find the intersections
     * @return a list of GeoPoints representing the intersections between the ray and the tube geometry,
     *         or {@code null} if no intersections are found
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector dir = ray.getDir();
        Vector v = axisRay.getDir();
        double dirV = dir.dotProdouct(v);

        if (ray.getP0().equals(axisRay.getP0())) { // In case the ray starts on the p0.
            if (isZero(dirV))
                return List.of(new GeoPoint(this, ray.getPoint(radius)));

            if (dir.equals(v.scale(dir.dotProdouct(v))))//הקרן פונה לכיוון או הרחק מהצילינדר מבלי לחצות אותו. במקרה זה, 'null' מוחזר כדי לציין שאין צמתים.
                return null;

            return List.of(new GeoPoint(this, ray.getPoint(Math.sqrt(radius * radius
                    / dir.subtract(v.scale(dir.dotProdouct(v)))
                    .lengthSquared()))));
        }

        Vector deltaP = ray.getP0().subtract(axisRay.getP0());
        double dpV = deltaP.dotProdouct(v);

        double a = 1 - dirV * dirV;
        double b = 2 * (dir.dotProdouct(deltaP) - dirV * dpV);
        double c = deltaP.lengthSquared() - dpV * dpV - radius * radius;

        if (isZero(a)) {
            if (isZero(b)) { // If a constant equation.
                return null;
            }
            return List.of(new GeoPoint(this, ray.getPoint(-c / b))); // if it's linear, there's a solution.
        }

        double discriminant = alignZero(b * b - 4 * a * c);

        if (discriminant < 0) // No real solutions.
            return null;

        double t1 = alignZero(-(b + Math.sqrt(discriminant)) / (2 * a)); // Positive solution.
        double t2 = alignZero(-(b - Math.sqrt(discriminant)) / (2 * a)); // Negative solution.

        if (discriminant <= 0) // No real solutions.
            return null;

        if (t1 > 0 && t2 > 0) {
            List<GeoPoint> _points = new ArrayList<>(2);
            _points.add(new GeoPoint(this, ray.getPoint(t1)));
            _points.add(new GeoPoint(this, ray.getPoint(t2)));
            return _points;
        }
        else if (t1 > 0) {
            List<GeoPoint> _points = new ArrayList<>(1);
            _points.add(new GeoPoint(this, ray.getPoint(t1)));
            return  _points;
        }
        else if (t2 > 0) {
            List<GeoPoint> _points = new ArrayList<>(1);
            _points.add(new GeoPoint(this, ray.getPoint(t2)));
            return _points;
        }
        return null;
    }}
