package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    public DirectionalLight(Color intensity,Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    /**
     * @param p the point in space
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * @param p the point in space
     * @return
     */
    @Override
    public Vector getL(Point p) {//לבדוק
        return direction.normalize();
    }

}
