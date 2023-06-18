package scene;
import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;

import java.util.LinkedList;
import java.util.List;


public class Scene {
    //the name of the scene
    String name;
    //The background color of the scene.
    public Color background=Color.BLACK;
    //The ambient light of the scene.
    public AmbientLight ambientLight = AmbientLight.NONE;
    //The geometries that in the scene
    public Geometries geometries = new Geometries();
    //The lights of the scene.
    public List<LightSource> lights = new LinkedList<>();
    /**
     * Getter for the name of the scene.
     *
     * @return The name of the scene.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the background of the scene.
     *
     * @return The background color of the scene.
     */
    public Color getBackground() {
        return this.background;
    }

    /**
     * Getter for the ambient light of the scene.
     *
     * @return The ambient light.
     */
    public AmbientLight getAmbientLight() {
        return this.ambientLight;
    }

    /**
     * Getter for the geometries structures in the scene.
     *
     * @return The geometries object.
     */
    public Geometries getGeometries() {
        return this.geometries;
    }
    /**
     * Getter for the lights in the scene.
     *
     * @return The lights.
     */
    public List<LightSource> getLights() {
        return this.lights;
    }

    /**
     * Constructs a new Scene with the specified name.
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight the ambient light to be set
     * @return the Scene object with the ambient light set
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background the background color to be set
     * @return the Scene object with the background color set
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the geometries in the scene.
     *
     * @param geometries the geometries to be set
     * @return the Scene object with the geometries set
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Sets the light sources in the scene.
     *
     * @param lights the light sources to be set
     * @return the Scene object with the light sources set
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

}
