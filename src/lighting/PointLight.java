package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    //declar varibles
    private final Point position;
    private double kc = 1;
    private double kl = 0;
    private double kq = 0;
    /**
     * Constructor that sets the light's intensity.
     *
     * @param intensity the light's intensity.
     * @param position  the light's position.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }
//get
     /**
     * @param p the point in space
     * @return color
     */
    /**
     * Returns the intensity of the light at the given point.
     *
     * @param p the point to get the intensity at
     * @return the intensity of the light at the given point
     */
    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        return getIntensity().scale(1/(kc + kl * d + kq * d * d));
    }
    /**
     * @param p the point in space
     * @return vector
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
//set builder pattern

    /**
     * setter for kC
     *
     * @param kc - new value for kC
     * @return this PointLight for builder pattern
     */
    @SuppressWarnings("unused")
    public PointLight setKc(double kc) {
        this.kc = kc;
        return this;
    }
    /**
     * setter for kl
     *
     * @param kl - new value for kl
     * @return this PointLight for builder pattern
     */
    public PointLight setKl(double kl) {
        this.kl = kl;
        return this;
    }
    /**
     * setter for kq
     *
     * @param kq - new value for kq
     * @return this PointLight for builder pattern
     */
    public PointLight setKq(double kq) {
        this.kq = kq;
        return this;
    }
}
