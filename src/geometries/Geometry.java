package geometries;
import primitives.*;

import java.util.List;

/**
 * An interface representing a geometric object that can be rendered in a 3D space.
 */
public abstract class Geometry extends Intersectable{
    /**

     Sets the emission color of the geometry.
     @param emission The new emission color to be set.
     @return The modified geometry object.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**

     Returns the emission color.
     @return The emission color.
     */
    public Color getEmission() {
        return emission;
    }
    protected Color emission =Color.BLACK;
    /**
     * Calculates and returns the normal vector to the surface of the geometry at the specified point.
     *
     * @param p0 the point on the surface of the geometry to calculate the normal vector for
     * @return the normal vector to the surface of the geometry at the specified point
     */
    public abstract Vector getNormal(Point p0);


}
