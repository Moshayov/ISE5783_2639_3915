package geometries;
import primitives.*;
/**
 * An interface representing a geometric object that can be rendered in a 3D space.
 */
public interface Geometry {
    /**
     * Calculates and returns the normal vector to the surface of the geometry at the specified point.
     *
     * @param p0 the point on the surface of the geometry to calculate the normal vector for
     * @return the normal vector to the surface of the geometry at the specified point
     * @throws Exception if an error occurs while calculating the normal vector
     */
    public  Vector getNormal(Point p0) throws Exception;
}
