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
        //two spheres for eyes
        scene1.getGeometries().add(new Sphere(new Point(20, 150, -5), 10d).setEmission(new Color(BLACK).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(-20, 150, -5), 10d).setEmission(new Color(BLACK).reduce(2)).
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

        // clouds
       // scene1.getGeometries().add(new Sphere(new Point(-300, 200, -150), 50d).setEmission(new Color(WHITE)).
                //setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        //scene1.getGeometries().add(new Sphere(new Point(-200, 220, -100), 60d).setEmission(new Color(WHITE)).
                //setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        //scene1.getGeometries().add(new Sphere(new Point(-100, 200, -150), 50d).setEmission(new Color(WHITE)).
                //setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        //scene1.getGeometries().add(new Sphere(new Point(100, 220, -100), 60d).setEmission(new Color(WHITE)).
                //setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        //scene1.getGeometries().add(new Sphere(new Point(200, 200, -150), 50d).setEmission(new Color(WHITE)).
                //setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        //cloud right
        scene1.getGeometries().add(new Sphere(new Point(490, 350, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(450, 400, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(400, 350, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(350, 403, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(310, 350, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        //cloud left
        scene1.getGeometries().add(new Sphere(new Point(-200, 320, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(-240, 370, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(-290, 320, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(-340, 373, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(-380, 320, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        // Add a sun
        scene1.getGeometries().add(new Sphere(new Point(0, 1000, -3000), 200d).setEmission(new Color(YELLOW)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));

        // snowflakes
        int numFlakes = 200; // adjust the number of snowflakes
        double minX = -700; // minimum X coordinate
        double maxX = 700; // maximum X coordinate
        double minY = 90; // minimum Y coordinate
        double maxY = 700; // maximum Y coordinate
        double minZ = -700; // minimum Z coordinate
        double maxZ = 700; // maximum Z coordinate
        double minRadius = 2; // minimum snowflake radius
        double maxRadius = 8; // maximum snowflake radius

        for (int i = 0; i < numFlakes; i++) {
            double x = Math.random() * (maxX - minX) + minX;
            double y = Math.random() * (maxY - minY) + minY;
            double z = Math.random() * (maxZ - minZ) + minZ;
            double radius = Math.random() * (maxRadius - minRadius) + minRadius;
            scene1.getGeometries().add(new Sphere(new Point(x, y, z), radius).setEmission(new Color(WHITE)).
                    setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        }

        // snowflakes
        int numFlakes2 = 200; // adjust the number of snowflakes
        double minX2 = -600; // minimum X coordinate
        double maxX2 = 600; // maximum X coordinate
        double minY2 = 100; // minimum Y coordinate
        double maxY2 = 600; // maximum Y coordinate
        double minZ2 = -600; // minimum Z coordinate
        double maxZ2 = 600; // maximum Z coordinate
        double minRadius2 = 0.5; // minimum snowflake radius
        double maxRadius2 = 2; // maximum snowflake radius

        for (int i = 0; i < numFlakes2; i++) {
            double x = Math.random() * (maxX2 - minX2) + minX2;
            double y = Math.random() * (maxY2 - minY2) + minY2;
            double z = Math.random() * (maxZ2 - minZ2) + minZ2;
            double radius = Math.random() * (maxRadius2 - minRadius2) + minRadius2;
            scene1.getGeometries().add(new Sphere(new Point(x, y, z), radius).setEmission(new Color(WHITE)).
                    setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        }


        // snowflakes
        int numFlakes3 = 500; // adjust the number of snowflakes
        double minX3 = -700; // minimum X coordinate
        double maxX3 = 700; // maximum X coordinate
        double minY3 = 80; // minimum Y coordinate
        double maxY3 = 700; // maximum Y coordinate
        double minZ3 = -700; // minimum Z coordinate
        double maxZ3 = 500; // maximum Z coordinate
        double minRadius3 = 0.5; // minimum snowflake radius
        double maxRadius3 = 2; // maximum snowflake radius

        for (int i = 0; i < numFlakes3; i++) {
            double x = Math.random() * (maxX3 - minX3) + minX3;
            double y = Math.random() * (maxY3 - minY3) + minY3;
            double z = Math.random() * (maxZ3 - minZ3) + minZ3;
            double radius = Math.random() * (maxRadius3 - minRadius3) + minRadius3;
            scene1.getGeometries().add(new Sphere(new Point(x, y, z), radius).setEmission(new Color(WHITE)).
                    setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        }



        // 3 spheres of the base little snowman
        scene1.getGeometries().add(new Sphere(new Point(500, -152, -50), 21.5d).setEmission(new Color(WHITE).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(500, -206, -50), 40d).setEmission(new Color(WHITE).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(500, -290, -50), 55d).setEmission(new Color(WHITE).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));

        // two spheres for little eyes
        scene1.getGeometries().add(new Sphere(new Point(502, -135, -10), 3.25d).setEmission(new Color(BLACK).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(488, -135, -10), 3.25d).setEmission(new Color(BLACK).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        // two spheres for little buttons
        scene1.getGeometries().add(new Sphere(new Point(485, -200, 50), 7d).setEmission(new Color(RED).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(485, -180, 50), 7d).setEmission(new Color(RED).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        // cylinder for little nose
        //  carrot nose
        scene1.getGeometries().add(new Sphere(new Point(500, -152, 0), 3d).setEmission(new Color(orange).reduce(2)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));



        // Add two bushes covered in snow
        // Bush 1
        //scene1.getGeometries().add(new Cylinder(new Ray(new Point(-200, -350, -100), new Vector(0, 0, -1)),40,100)
                //.setEmission(new Color(34, 139, 34))
              //  .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));

        //scene1.getGeometries().add((new Sphere(
              //  new Point(-200, -350, -200),
             //   60)
                //.setEmission(new Color(34, 139, 34))).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));

        // Bush 2
       // scene1.getGeometries().add(new Cylinder(
             //   new Ray(new Point(300, -350, -100),new Vector(0, 0, -1)),
               // 50,
               // 120
              //  )
               // .setEmission(new Color(red))
              //  .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));

       // scene1.getGeometries().add(new Sphere(
                //new Point(300, -350, -220),
                //80)
                //.setEmission(new Color(34, 139, 34))
                //.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));


        //try bush
        //scene1.getGeometries().add(new Triangle(new Point(-100, -150, 40),new Point(-100,-210,-20),new Point(100,-210,-100)).setEmission(new Color(RED).reduce(2)).
                //setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));

        // Green hat (triangle shape)
        Geometry hat = new Triangle(
                new Point(-45, 150, -50),
                new Point(45, 150, -50),
                new Point(0, 200, -50)
        ).setEmission(new Color(red)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(hat);

        //big hat
        Geometry hat2 = new Triangle(
                new Point(-45, 150, -50),
                new Point(45, 150, -50),
                new Point(0, 210, -50)
        ).setEmission(new Color(white)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(hat2);

        //grass
        Geometry grass = new Triangle(
                new Point(-300, -430, -50),
                new Point(-260, -430, -50),
                new Point(-280, -300, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass);
        //
        Geometry grass2 = new Triangle(
                new Point(-270, -430, -50),
                new Point(-260, -430, -50),
                new Point(-265, -300, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass2);

        return scene1;
    }//

    /**
     * Produce a scenecolor ball between 4 frames.
     */
    @Test
    public void bonus10Geo() {
        setGeo().getLights().add(new PointLight(new Color(150, 150, 150), new Point(500, 500, 6000)));


        camera1.setImageWriter(new ImageWriter("snow_mann", 500, 500))
                .setRayTracer(new RayTracerBasic(scene1))
                .renderImage();
        camera1.writeToImage();
    }

}
