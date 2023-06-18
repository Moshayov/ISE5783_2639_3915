package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Scene {

    //The background color of the scene.
    public Color background;
    //The ambient light of the scene.

    public AmbientLight ambientLight;
    //The geometries that in the scene

    public Geometries geometries;
    //The lights of the scene.
    public List<LightSource> lights = new LinkedList<>();
    //the name of the scene
    String name;

    /**
     * Constructs a new Scene with the specified name.
     *
     * @param name the name of the scene
     */
    /**
     *
     * @param builder The builder object.
     */
    public Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights = builder.lights;

    }

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
     * Sets the light sources in the scene.
     *
     * @param lights the light sources to be set
     * @return the Scene object with the light sources set
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
    public static class SceneBuilder {

        public  String name;
        public Color background = Color.BLACK;
        public AmbientLight ambientLight = AmbientLight.NONE;
        public Geometries geometries = new Geometries();
        public List<LightSource> lights = new ArrayList<>();


        /**
         * This is the builder
         *
         * @param name The name of the scene
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        // chaining method

        /**
         * This function sets the background color of the scene and returns the scene builder.
         *
         * @param background The background color of the scene.
         * @return The SceneBuilder object.
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        /**
         * > Sets the ambient light of the scene
         *
         * @param ambientLight The ambient light of the scene.
         * @return The SceneBuilder object.
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * This function sets the geometries of the scene
         *
         * @param geometries The geometries of the scene.
         * @return The SceneBuilder object.
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        /**
         * This function returns the scene that we built.
         *
         * @return A new instance of the Scene class.
         */
        public Scene build() {
            return new Scene(this);
        }

}
