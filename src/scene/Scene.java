package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;

public class Scene {
    String name;
    Color background=Color.BLACK;
    AmbientLight ambientLight = AmbientLight.NONE;
    Geometries geometries = new Geometries();

    public Scene(String name) {
        this.name=name;
    }
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

}
