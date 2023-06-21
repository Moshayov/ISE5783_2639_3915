package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube {
    /*final private double height;*/
    double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if(radius<=0)
            throw new IllegalArgumentException("The radius low then zero!");
        if(height<=0)
            throw new IllegalArgumentException("The height low equal to zero!");
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
            //return v;
            return v.scale(-1);

        // projection of P-p0 on the ray:
        Vector u = point.subtract(p0);

        // distance from p0 to the o who is in from of point
        double t = alignZero(u.dotProdouct(v));

        // if the point is at a base
        //if (t == 0 || isZero(height - t))
            //return v;

        if(isZero(t)){
            return v.scale(-1);
        }
        //check if the point on the top
        if(isZero(t-height)){
            return v;
        }
        //the other point on the axis facing the given point
        //Point o = axisRay.getPoint(t);
        Point o=p0.add(v.scale(t));

        //create the normal vector
        return point.subtract(o).normalize();
    }

   // public double getHeight() {
       // return height;
    //}

    @Override
    public String toString() {
        return "Cylinder{" +
                super.toString()+
                "height=" + height +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (/*o == null ||*/ !(o instanceof Cylinder cylinder)) return false;
        if (!super.equals(o)) return false;

        return this.height==cylinder.height;
    }

}
