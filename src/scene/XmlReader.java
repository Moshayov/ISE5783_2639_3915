package scene;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import geometries.Geometries;
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
import static org.junit.jupiter.api.Assertions.fail;


public  class XmlReader {


    /**
     * This function builds a scene from an xml file.
     *
     * @param file The xml file.
     * @return The SceneBuilder object.
     */
    public static Scene XmlScene(File file, Scene scene) {
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
        scene.setBackground(backgroundColor);

        Element ambientLightElement = (Element) root.getElementsByTagName("ambient-light").item(0);
        String[] ambientLightString = ambientLightElement.getAttribute("color").split(" ");
        double ambientLight0 = Double.parseDouble(backgroundColorString[0]);
        double ambientLight1 = Double.parseDouble(backgroundColorString[1]);
        double ambientLight2 = Double.parseDouble(backgroundColorString[2]);
        Color ambientLightColor = new Color(ambientLight0, ambientLight1, ambientLight2);
        AmbientLight ambientLight = new AmbientLight(ambientLightColor, new Double3(1, 1, 1));
        scene.setAmbientLight(ambientLight);

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
        scene.setGeometries(geometries);
        return scene;
    }
}
