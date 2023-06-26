package renderer;
import geometries.*;
import lighting.PointLight;
import primitives.*;
import renderer.ImageWriter;
import renderer.*;
import scene.Scene;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

public class Tryy {
    Scene scene1 = new Scene.SceneBuilder("snowman") .build();
    Camera camera1 = new Camera(new Point(-1000, 1000, 8000), new Vector(0.13, -0.13, -1),
            new Vector(0, 1, -0.13))
            .setVPSize(150, 150).setVPDistance(1000)
            .setRayTracer(new RayTracerBasic(scene1));

    private Scene setGeo() {

        // shiny plane
        scene1.getGeometries().add(
                new Plane(
                        new Point(0, -350, 0), new Vector(0,-350,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKr(0.2).setnShines(60))
                        .setEmission(new Color(GRAY)));
        // 3 spheres of the base snowman
        scene1.getGeometries().add(new Sphere(new Point(0, -200, -50), 150d).setEmission(new Color(WHITE).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(0, 0, -50), 100d).setEmission(new Color(WHITE).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(0, 125, -50), 50d).setEmission(new Color(WHITE).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        // two spheres for eyes
        scene1.getGeometries().add(new Sphere(new Point(20, 150, -10), 10d).setEmission(new Color(BLACK).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(-20, 150, -10), 10d).setEmission(new Color(BLACK).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        // two spheres for buttons
        scene1.getGeometries().add(new Sphere(new Point(0, -25, 50), 15d).setEmission(new Color(RED).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(0, 25, 50), 15d).setEmission(new Color(RED).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        // cylinder for nose
        // carrot nose
        scene1.getGeometries().add(new Sphere(new Point(0, 125, 0), 10d).setEmission(new Color(orange).reduce(2)).
               setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        // Green hat (triangle shape)
        Geometry hat = new Triangle(
                new Point(-50, 165, -50),
                new Point(50, 165, -50),
                new Point(0, 240, -50)
        ).setEmission(new Color(GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(hat);

        // snowflakes
        int numFlakes = 200; // adjust the number of snowflakes
        double minX = -500; // minimum X coordinate
        double maxX = 500; // maximum X coordinate
        double minY = 100; // minimum Y coordinate
        double maxY = 500; // maximum Y coordinate
        double minZ = -500; // minimum Z coordinate
        double maxZ = 500; // maximum Z coordinate
        double minRadius = 2; // minimum snowflake radius
        double maxRadius = 10; // maximum snowflake radius

        for (int i = 0; i < numFlakes; i++) {
            double x = Math.random() * (maxX - minX) + minX;
            double y = Math.random() * (maxY - minY) + minY;
            double z = Math.random() * (maxZ - minZ) + minZ;
            double radius = Math.random() * (maxRadius - minRadius) + minRadius;
            scene1.getGeometries().add(new Sphere(new Point(x, y, z), radius).setEmission(new Color(WHITE)).
                    setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        }

        return scene1;
    }

    /**
     * Produce a scenecolor ball between 4 frames.
     */
    @Test
    public void bonus10Geo() {
        setGeo().getLights().add(new PointLight(new Color(150, 150, 150), new Point(500, 500, 6000)));


        camera1.setImageWriter(new ImageWriter("snow_man2", 500, 500))
                .setRayTracer(new RayTracerBasic(scene1))
                .renderImage();
        camera1.writeToImage();
    }

}
