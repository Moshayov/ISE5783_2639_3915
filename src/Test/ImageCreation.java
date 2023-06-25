package Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.*;

public class ImageCreation {
    public static void main(String[] args) {
        Scene scene = new Scene.SceneBuilder("Test scene")
                .setBackground(new Color(102,204,0))
                .setAmbientLight(new AmbientLight(new Color(0,76,153),0.9))
                        .build();
        Camera camera = new Camera(new Point(0, 0, -10), new Vector(0 ,0,-1),
                new Vector(0,1,0))
                .setVPSize(800, 800).setVPDistance(100) //
                .setRayTracer(new RayTracerBasic(scene));

        // Create the clouds
        Geometry cloud1 = new Sphere(new Point(100, 100, 100), 50)
                .setEmission(new Color(255, 255, 255)) // White color
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(30));
        Geometry cloud2 = new Sphere(new Point(-100, -100, 200), 70)
                .setEmission(new Color(255, 255, 255)) // White color
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(30));

        // Create the grass
        Geometry grass = new Plane(new Point(0, 0, -10), new Vector(0, 0, 1))
                .setEmission(new Color(0, 128, 0)) // Green color
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(30));

        // Create the tree trunk
        Geometry trunk = new Cylinder(new Ray(new Point(0, 0, -50), new Vector(0, 0, -1)), 10, 100)
                .setEmission(new Color(139, 69, 19)) // Brown color
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(30));

        // Create the tree leaves
        Geometry leaves = new Sphere(new Point(0, 0, 0), 60)
                .setEmission(new Color(0, 128, 0)) // Green color
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(30));

        // Create the apples
        Geometry apple1 = new Sphere(new Point(30, 50, 80), 10)
                .setEmission(new Color(255, 0, 0)) // Red color
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(30));
        Geometry apple2 = new Sphere(new Point(-20, 40, 100), 12)
                .setEmission(new Color(255, 0, 0)) // Red color
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(30));

        // Add all the geometries to the scene
        //scene.geometries.add(cloud1, cloud2, grass, trunk, leaves, apple1, apple2);
        scene.geometries.add(new Plane(new Point(0,-0.5,1),new Vector(0.002,0.5,0.4)));
        // Add lights to the scene
        scene.lights.add(
                new PointLight(new Color(255, 255, 255), new Point(0, -200, 200))
                        .setKl(1E-4).setKq(1.5E-5)
        );
        camera.setImageWriter(new ImageWriter("miniProject555", 800, 800)) //
                .renderImage() //
                .writeToImage();
    }
}

