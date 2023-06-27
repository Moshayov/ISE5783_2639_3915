package lighting;

import primitives.Color;

/**
 * Represents a Light object with an intensity of a specific color.
 */
abstract class Light {
    private Color intensity;

    /**
     * Constructs a Light object with the specified intensity.
     *
     * @param intensity the color intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Retrieves the intensity of the light.
     *
     * @return the color intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }

}

