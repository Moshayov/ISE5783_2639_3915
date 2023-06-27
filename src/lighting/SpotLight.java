package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.isZero;

public class SpotLight extends PointLight {
    private final Vector direction;
    private double narrowBeam = 1d;

    /**
     * Constructor that sets the light's intensity.
     * @param intensity the light's intensity.
     * @param position  the light's position.
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * {@inheritDoc}
     *
     * Overrides the {@code getIntensity} method in the superclass.
     * Calculates the intensity of the light at a given point based on the direction of the light and the normal vector at the point.
     *
     * @param p the point at which to calculate the intensity
     * @return the intensity of the light at the given point
     */
    @Override
    public Color getIntensity(Point p) {
        double factor = 0d;
        double l = direction.dotProdouct(getL(p));
        if (isZero(l)) {
            return Color.BLACK;
        }
        if (l > 0) {
            factor = Math.pow(l, narrowBeam);
            return super.getIntensity(p).scale(factor);
        }
        return Color.BLACK;
    }


    /**
     * Setter for the narrowBeam field.
     *
     * @param narrowBeam The angle of the narrow beam in degrees.
     * @return The object itself.
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }
}
