package geometries;
import primitives.*;

import java.util.List;

/**
 * An interface representing a geometric object that can be rendered in a 3D space.
 */
public interface Geometry extends Intersectable{
    /**
     * Calculates and returns the normal vector to the surface of the geometry at the specified point.
     *
     * @param p0 the point on the surface of the geometry to calculate the normal vector for
     * @return the normal vector to the surface of the geometry at the specified point
     */
    public  Vector getNormal(Point p0);


}
