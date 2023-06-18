package geometries;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends RadialGeometry {
    protected Ray axisRay;

    /**
     * Constructor for Tube class with a ray and a radius
     *
     * @param axisRay ray value
     * @param radius  radius value
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Calculates the normal vector at a given point on the surface of the object.
     *
     * @param point the point on the surface of the object
     * @return the normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        {
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
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
