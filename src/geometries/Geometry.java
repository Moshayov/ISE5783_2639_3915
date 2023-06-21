package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * An interface representing a geometric object that can be rendered in a 3D space.
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

    private Material material = new Material();

    /**
     * Calculates and returns the normal vector to the surface of the geometry at the specified point.
     *
     * @param p0 the point on the surface of the geometry to calculate the normal vector for
     * @return the normal vector to the surface of the geometry at the specified point
     */
    public abstract Vector getNormal(Point p0);

    /**
     * Retrieves the material of the object.
     *
     * @return the Material object representing the material of the object
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material the Material object representing the material to be set
     * @return the Geometry object with the material set
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Returns the emission color.
     *
     * @return The emission color.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission The new emission color to be set.
     * @return The modified geometry object.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }




}
