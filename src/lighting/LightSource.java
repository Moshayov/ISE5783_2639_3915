package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
public interface LightSource {
    /**
     * Retrieves the intensity of the light at the specified point in space.
     *
     * @param p the point in space
     * @return the color intensity of the light at the specified point
     */
     public Color getIntensity(Point p);
    /**
     * Retrieves the direction vector from the specified point towards the light source.
     *
     * @param p the point in space
     * @return the direction vector from the point towards the light source
     */
    public Vector getL(Point p);

}
