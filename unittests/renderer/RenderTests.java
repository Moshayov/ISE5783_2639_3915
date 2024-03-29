package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import scene.Scene;

import org.junit.jupiter.api.Test;
import static java.awt.Color.*;


import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import java.io.File;
import static java.awt.Color.YELLOW;

/** Test rendering a basic image
 * @author Dan */
public class RenderTests {

    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene(new Scene.SceneBuilder("Test scene")
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191),
                        new Double3(1, 1, 1))) //
                .setBackground(new Color(75, 127, 90)));

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
    public void basicRenderMultiColorTest() {
        Scene scene = new Scene(new Scene.SceneBuilder("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2)))); //

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
                .setImageWriter(new ImageWriter("color render test2", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(new Color(WHITE), 100);
        camera.writeToImage();
    }

    /*
     * Produce from a xml file a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void basicRenderXml() {
        File inputFile = new File("basicRenderTestTwoColors.xml");
        Scene.SceneBuilder scene = new Scene.SceneBuilder("XML Test scene").XmlScene(inputFile);
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500)
                .setImageWriter(new ImageWriter("xml render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene.build()));
        camera.renderImage();
        camera.printGrid(new Color(java.awt.Color.YELLOW), 100);
        camera.writeToImage();
    }
}
