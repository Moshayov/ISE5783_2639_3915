package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    /**
     * Returns the intensity of the light at the given point.
     *
     * @param p the point to get the intensity at
     * @return the intensity of the light at the given point
     */
    private Point position;
    private double kc = 1d;
    private double kl = 0d;
    private double kq = 0d;
    /**
     * Constructs a Light object with the specified intensity.
     *
     * @param intensity the color intensity of the light
     */

    /**
     * @param p the point in space
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        return null;
    }

    /**
     * @param p the point in space
     * @return
     */
    @Override
    public Vector getL(Point p) {
        return null;
    }

}
