package geometries;
import primitives.*;
public class Tube extends RadialGeometry{
  protected   Ray axisRay;
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
    @Override
    public Vector getNormal(Point point) throws Exception {
       return null;
    }

}
