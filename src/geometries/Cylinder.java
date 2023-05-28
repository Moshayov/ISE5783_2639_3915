package geometries;
import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube{
    /**
     * Returns the normal vector at the given point on the surface of the cylinder.
     *
     * @param point the point on the surface of the cylinder to calculate the normal vector for
     * @return the normal vector at the given point
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
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        //if the point and p0 are the same
        if (point.equals(p0))
            return v;

        // projection of P-p0 on the ray:
        Vector u = point.subtract(p0);

        // distance from p0 to the o who is in from of point
        double t = alignZero(u.dotProdouct(v));

        // if the point is at a base
        if (t == 0 || isZero(height - t))
            return v;

        //the other point on the axis facing the given point
        Point o = axisRay.getPoint(t);

        //create the normal vector
        return point.subtract(o).normalize();
    }

}
