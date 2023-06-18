package renderer;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;
import scene.XmlReader;

import java.io.File;

import static java.awt.Color.*;
import static java.awt.Color.WHITE;

/** Test rendering a basic image
 * @author Dan */
public class RenderTests {

    /** Produce a scene with basic 3D model and render it into a png image with a
     * grid */
    @Test
    public void basicRenderTwoColorTest() {

        Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(255, 191, 191),new Double3(1, 1, 1))).setBackground(new Color(75, 127, 90));
        scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                        new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        // right
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("base render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(new Color(YELLOW), 100);
        camera.writeToImage();
    }

    // For stage 6 - please disregard in stage 5
    /** Produce a scene with basic 3D model - including individual lights of the
     * bodies and render it into a png image with a grid */
    @Test
    public void basicRenderMultiColorTest() throws IllegalAccessException {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //

        scene.geometries.add( // center
                new Sphere(new Point(0, 0, -100), 50),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));

        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("color render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(new Color(WHITE), 100);
        camera.writeToImage();
    }
    /** Test for XML based scene - for bonus */
    @Test
    public void basicRenderXml() throws IllegalAccessException {
        Scene  scene  = new Scene("XML Test scene");
        File file = new File("c:/mini_Project/basicRenderTestTwoColors.xml");
        XmlReader.XmlScene(file,scene);
        // enter XML file name and parse from XML file into scene object
        // using the code you added in appropriate packages
        // ...
        // NB: unit tests is not the correct place to put XML parsing code

        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))     //
                .setVPDistance(100)                                                                //
                .setVPSize(500, 500).setImageWriter(new ImageWriter("xml render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(new Color(YELLOW), 100);
        camera.writeToImage();
    }
}
