package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light{
    /**
     * A constant representing no ambient light, with an intensity of black and a reflection coefficient of zero.
     */
   public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    /**
     * Constructs an AmbientLight object with the specified ambient intensity and reflection coefficient.
     *
     * @param Ia the ambient intensity of the light
     * @param Ka the ambient reflection coefficient
     */
    public AmbientLight(Color Ia, Double3 Ka) {
       super(Ia.scale(Ka));
    }
    /**
     * Constructs an AmbientLight object with the specified ambient intensity and reflection coefficient.
     *
     * @param Ia the ambient intensity of the light
     * @param Ka the ambient reflection coefficient
     */
    public AmbientLight(Color Ia, Double Ka) {
        super(Ia.scale(Ka));
    }

}
