package scene;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.LightSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


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

    /*
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

    /**
     * Sets the ambient light in the scene.
     *
     * @param ambientLight the ambient light to be set in the scene
     * @return the updated Scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public static class SceneBuilder {

        public String name;
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

        /**
         * Sets the list of light sources in the scene builder.
         *
         * @param lights the list of light sources to be set in the scene builder
         * @return the updated SceneBuilder object
         */
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

        /**
         * This function builds a scene from an xml file.
         *
         * @param file The xml file.
         * @return The SceneBuilder object.
         */
        public SceneBuilder XmlScene(File file) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            Document doc = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(file);
            } catch (ParserConfigurationException e) {
                fail("ParserConfigurationException");
            } catch (SAXException e) {
                fail("SAXException");
            } catch (IOException e) {
                fail("IOException");
            }
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            String[] backgroundColorString = root.getAttribute("background-color").split(" ");
            double backgroundColor0 = Double.parseDouble(backgroundColorString[0]);
            double backgroundColor1 = Double.parseDouble(backgroundColorString[1]);
            double backgroundColor2 = Double.parseDouble(backgroundColorString[2]);
            Color backgroundColor = new Color(backgroundColor0, backgroundColor1, backgroundColor2);
            this.setBackground(backgroundColor);

            Element ambientLightElement = (Element) root.getElementsByTagName("ambient-light").item(0);
            String[] ambientLightString = ambientLightElement.getAttribute("color").split(" ");
            double ambientLight0 = Double.parseDouble(backgroundColorString[0]);
            double ambientLight1 = Double.parseDouble(backgroundColorString[1]);
            double ambientLight2 = Double.parseDouble(backgroundColorString[2]);
            Color ambientLightColor = new Color(ambientLight0, ambientLight1, ambientLight2);
            AmbientLight ambientLight = new AmbientLight(ambientLightColor, new Double3(1, 1, 1));
            this.setAmbientLight(ambientLight);

            Geometries geometries = new Geometries();
            Node geometriesNode = root.getElementsByTagName("geometries").item(0);
            NodeList geometriesNodes = geometriesNode.getChildNodes();
            for (int i = 0; i < geometriesNodes.getLength(); i++) {
                Node geometryNode = geometriesNodes.item(i);
                if (geometryNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element geometryElement = (Element) geometryNode;
                switch (geometryElement.getNodeName()) {
                    case "sphere" -> {
                        String[] centerString = geometryElement.getAttribute("center").split(" ");
                        double centerx = Double.parseDouble(centerString[0]);
                        double centery = Double.parseDouble(centerString[1]);
                        double centerz = Double.parseDouble(centerString[2]);
                        Point center = new Point(centerx, centery, centerz);
                        Double radius = Double.parseDouble(geometryElement.getAttribute("radius"));
                        Sphere sphere = new Sphere(center, radius);
                        geometries.add(sphere);
                    }
                    case "triangle" -> {
                        String[] p0String = geometryElement.getAttribute("p0").split(" ");
                        double p0x = Double.parseDouble(p0String[0]);
                        double p0y = Double.parseDouble(p0String[1]);
                        double p0z = Double.parseDouble(p0String[2]);
                        Point p0 = new Point(p0x, p0y, p0z);
                        String[] p1String = geometryElement.getAttribute("p1").split(" ");
                        double p1x = Double.parseDouble(p1String[0]);
                        double p1y = Double.parseDouble(p1String[1]);
                        double p1z = Double.parseDouble(p1String[2]);
                        Point p1 = new Point(p1x, p1y, p1z);
                        String[] p2String = geometryElement.getAttribute("p2").split(" ");
                        double p2x = Double.parseDouble(p2String[0]);
                        double p2y = Double.parseDouble(p2String[1]);
                        double p2z = Double.parseDouble(p2String[2]);
                        Point p2 = new Point(p2x, p2y, p2z);
                        Triangle triangle = new Triangle(p0, p1, p2);
                        geometries.add(triangle);
                    }
                    default -> {
                    }
                }
            }
            this.setGeometries(geometries);
            return this;
        }
    }


}
