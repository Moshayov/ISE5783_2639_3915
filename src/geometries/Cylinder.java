package geometries;
import primitives.*;
public class Cylinder extends Tube{
    final private double height;
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) throws Exception {
        //  checks if it in the bases center to avoid zero vector
        return null;
    }

}
