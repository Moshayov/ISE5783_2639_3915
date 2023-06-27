package renderer;
import geometries.*;
import lighting.PointLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

public class Snow_man_Final_Image {
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
        //cloud right
        scene1.getGeometries().add(new Sphere(new Point(490, 350, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300).setKt(new Double3(0.5, 0, 0))));
        scene1.getGeometries().add(new Sphere(new Point(450, 400, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300).setKt(new Double3(0.5, 0, 0))));
        scene1.getGeometries().add(new Sphere(new Point(400, 350, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300).setKt(new Double3(0.5, 0, 0))));
        scene1.getGeometries().add(new Sphere(new Point(350, 403, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300)));
        scene1.getGeometries().add(new Sphere(new Point(310, 350, -150), 50d).setEmission(new Color(WHITE)).
                setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300).setKt(new Double3(0.5, 0, 0))));
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

        // red hat (triangle shape)
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
                new Point(-270, -430, -50),
                new Point(-285, -290, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass);
        //
        Geometry grass5 = new Triangle(
                new Point(-283, -430, -50),
                new Point(-270, -430, -50),
                new Point(-285, -230, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass5);

        //
        Geometry grass2 = new Triangle(
                new Point(-270, -430, -50),
                new Point(-260, -430, -50),
                new Point(-265, -300, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass2);
        //
        Geometry grass4 = new Triangle(
                new Point(-260, -430, -50),
                new Point(-240, -430, -50),
                new Point(-250, -200, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass4);

        //
        Geometry grass3 = new Triangle(
                new Point(-250, -430, -50),
                new Point(-220, -430, -50),
                new Point(-235, -180, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass3);
        //
        Geometry grass6 = new Triangle(
                new Point(-235, -430, -50),
                new Point(-200, -430, -50),
                new Point(-215, -290, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass6);
        //

        //grass 2
        Geometry grass7 = new Triangle(
                new Point(-350, -430, -190),
                new Point(-330, -430, -190),
                new Point(-340, -290, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass7);
        Geometry grass8 = new Triangle(
                new Point(-350, -430, -190),
                new Point(-380, -430, -190),
                new Point(-365, -290, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass8);
        //
        Geometry grass9 = new Triangle(
                new Point(-380, -430, -190),
                new Point(-400, -430, -190),
                new Point(-370, -230, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass9);

        //
        Geometry grass10 = new Triangle(
                new Point(-400, -430, -190),
                new Point(-430, -430, -190),
                new Point(-415, -300, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass10);
        //
        Geometry grass11 = new Triangle(
                new Point(-430, -430, -190),
                new Point(-450, -430, -190),
                new Point(-440, -200, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass11);

        //
        Geometry grass12 = new Triangle(
                new Point(-450, -430, -190),
                new Point(-474, -430, -190),
                new Point(-460, -180, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass12);
        //grass3
        Geometry grass13 = new Triangle(
                new Point(-490, -430, -30),
                new Point(-510, -430, -30),
                new Point(-500, -300, -30)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass13);
        //
        Geometry grass14 = new Triangle(
                new Point(-510, -430, -30),
                new Point(-522, -430, -30),
                new Point(-515, -200, -30)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass14);

        //
        Geometry grass15 = new Triangle(
                new Point(-522, -430, -30),
                new Point(-540, -430, -30),
                new Point(-530, -180, -30)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass15);


        //grass left1
        //grass 2
        Geometry grass16 = new Triangle(
                new Point(190, -430, -190),
                new Point(210, -430, -190),
                new Point(200, -290, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass16);
        Geometry grass17 = new Triangle(
                new Point(210, -430, -190),
                new Point(230, -430, -190),
                new Point(220, -290, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass17);
        //
        Geometry grass18 = new Triangle(
                new Point(230, -430, -190),
                new Point(260, -430, -190),
                new Point(240, -230, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass18);

        //
        Geometry grass19 = new Triangle(
                new Point(260, -430, -190),
                new Point(270, -430, -190),
                new Point(265, -300, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass19);
        //
        Geometry grass20 = new Triangle(
                new Point(270, -430, -190),
                new Point(299, -430, -190),
                new Point(285, -200, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass20);

        //
        Geometry grass21 = new Triangle(
                new Point(299, -430, -190),
                new Point(327, -430, -190),
                new Point(316, -180, -190)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass21);

        //grass left 2
        Geometry grass22 = new Triangle(
                new Point(327, -430, -50),
                new Point(347, -430, -50),
                new Point(337, -290, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass22);
        //
        Geometry grass23 = new Triangle(
                new Point(347, -430, -50),
                new Point(376, -430, -50),
                new Point(360, -230, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass23);

        //
        Geometry grass24 = new Triangle(
                new Point(376, -430, -50),
                new Point(396, -430, -50),
                new Point(387, -300, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass24);
        //
        Geometry grass25 = new Triangle(
                new Point(396, -430, -50),
                new Point(410, -430, -50),
                new Point(405, -200, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass25);

        //
        Geometry grass26 = new Triangle(
                new Point(415, -430, -50),
                new Point(440, -430, -50),
                new Point(428, -180, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass26);
        //
        Geometry grass27 = new Triangle(
                new Point(440, -430, -50),
                new Point(454, -430, -50),
                new Point(430, -250, -50)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass27);
        //grass left 3
        Geometry grass28 = new Triangle(
                new Point(600, -430, -30),
                new Point(620, -430, -30),
                new Point(610, -300, -30)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass28);
        //
        Geometry grass29 = new Triangle(
                new Point(620, -430, -30),
                new Point(650, -430, -30),
                new Point(635, -200, -30)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass29);

        //
        Geometry grass31 = new Triangle(
                new Point(650, -430, -30),
                new Point(670, -430, -30),
                new Point(660, -100, 100)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass31);

        Geometry grass30 = new Triangle(
                new Point(580, -430, -30),
                new Point(600, -430, -30),
                new Point(595, -180, -30)
        ).setEmission(new Color(24,112,14)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShines(300));
        scene1.getGeometries().add(grass30);
       Geometry spher = new Sphere( new Point(390, -300, -30),50d).setEmission(new Color(red)) //
                .setMaterial(new Material().setKd(0.25).setKs(0.25).setnShines(20)
                        .setKt(0.6));
       scene1.getGeometries().add(spher);



        return scene1;
    }//

    /**
     * Produce a scenecolor ball between 4 frames.
     */
    @Test
    public void bonus10Geo() {
        setGeo().getLights().add(new PointLight(new Color(150, 150, 150), new Point(500, 500, 6000)));
        scene1.getLights().add(new SpotLight(new Color(GREEN),new Point(-800, 1200, 5000),new Vector(-0.1,-1,-0.5)).setKl(2E-4).setKq(4E-5)
               );


        camera1.setImageWriter(new ImageWriter("snow_mann", 500, 500))
                .setRayTracer(new RayTracerBasic(scene1))
                .setAntiAliasing(true)
                .renderImage();
        camera1.writeToImage();
    }

}
