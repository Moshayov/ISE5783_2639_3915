package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
    //private final Vector direction;
    Vector direction;

    /**
     * Constructs a new DirectionalLight with the specified intensity and direction.
     *
     * @param intensity the color intensity of the light
     * @param direction the direction in which the light is emitted
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    /**
     * @param p the point in space
     * @return color
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * @param p the point in space
     * @return vector of direction
     */
    @Override
    public Vector getL(Point p) {//לבדוק
        return direction.normalize();
    }

    /**
     * @param point the point in space
     * @return distance
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

}
