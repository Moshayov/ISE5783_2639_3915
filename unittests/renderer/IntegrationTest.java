package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * The cameraIntersection method calculates the intersection points between
     * a geometric object and a camera.
     *
     * @param geometric The intersectable geometric object to check intersections with.
     * @param cam       The camera to check intersections from.
     * @param nX        The number of columns in the view plane.
     * @param nY        The number of rows in the view plane.
     * @return The total number of intersection points between the camera and the geometric object.
     */
    public int cameraIntersection(Intersectable geometric, Camera cam, int nX, int nY) {
        int sum = 0;
        //list - temporarily keeps the intersection points
        //useful when there are no intersection points, so we need to check if it's null
        List<Point> list;
        for (int i = 0; i < nY; i++)
            for (int j = 0; j < nX; j++) {
                list = geometric.findIntersections(cam.constructRay(nX, nY, i, j));
                if (list != null)
                    sum += list.size();
            }
        return sum;
    }

    /**
     * The spherIntergationtest method tests the intersection calculation
     * between a sphere and a camera.
     *
     * @throws IllegalAccessException If there is an illegal access to the method or field.
     */
    @Test
    void spherIntergationtest() throws IllegalAccessException {
        String message = "wrong number of intersection points";
        Sphere sp=new Sphere(new Point(0,0,-3),1d);
        Vector vTO = new Vector(0,0,-1);
        Vector vUP = new Vector(0,1,0);
        int nX = 3;
        int nY = 3;
        /*TCO1 2 intersection points r=1*/
        Camera camera = new Camera(ZERO_POINT,vTO,vUP);
        camera.setVPSize(nX,nY);
        camera.setVPDistance(1);
        assertEquals(2,cameraIntersection(sp,camera,nX,nY),message);
        /*TCO2 18 intersection points r=2.5*/
        Camera camera1 = new Camera(new Point(0,0,0.5), vTO,vUP);
        camera1.setVPDistance(1);
        camera1.setVPSize(nX,nY);
        assertEquals(18,cameraIntersection(new Sphere(new Point(0,0,-2.5),2.5),camera1,nX,nY ),message);
        /*TCO3 10 intersection points r=2*/
        assertEquals(10,cameraIntersection(new Sphere(new Point(0,0,-2),2),camera1,nX,nY ),message);
        /*TCO4 9 intersection points r=4*/
        assertEquals(9,cameraIntersection(new Sphere(new Point(0,0,-0.5),4),camera1,nX,nY ),message);
        /*TCO5 0 intersection points r=0.5*/
        assertEquals(0,cameraIntersection(new Sphere(new Point(0,0,1),0.5),camera,nX,nY ),message);
    }
    /**
     * Integration test for the Camera's intersection with Plane objects.
     * <p>
     * This method tests several scenarios of Plane objects, and verifies that the Camera
     * can intersect with them correctly. Each scenario is defined by a specific set of
     * input parameters, including the Plane's position and orientation, Camera position,
     * and resolution of the viewport. The method asserts that the correct number of
     * intersection points are found in each scenario.
     *
     * @throws IllegalAccessException if there is an error accessing a field or method.
     */
    @Test
    void planeIntergationtest() throws IllegalAccessException {
        String message = "wrong number of intersection points";
        Vector vTo = new Vector(0, 0, -1);
        Vector vUp = new Vector(0, 1, 0);
        int nX = 3;
        int nY = 3;
        /*TCO1 9 intersection points */
        Camera camera = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        camera.setVPSize(nX, nY);
        camera.setVPDistance(1);
        assertEquals(9,cameraIntersection(new Plane(new Point(1,1,-5),new Vector(0,0,1)),camera,nX,nY ),message);
        /*TCO2 9 intersection points */
        assertEquals(9,cameraIntersection(new Plane(new Point(1,1,-5),new Vector(0,1,-5)),camera,nX,nY ),message);
        /*TCO3 6 intersection points */
        assertEquals(6,cameraIntersection(new Plane(new Point(0,0,-5),new Vector(0,6,-5)),camera,nX,nY ),message);

    }
    /**
     * Integration test for the Camera's intersection with Triangle objects.
     * <p>
     * This method tests several scenarios of Triangle objects, and verifies that the Camera
     * can intersect with them correctly. Each scenario is defined by a specific set of
     * input parameters, including the Triangle's vertices, Camera position, and resolution
     * of the viewport. The method asserts that the correct number of intersection points
     * are found in each scenario.
     *
     * @throws IllegalAccessException if there is an error accessing a field or method.
     */
    @Test
    void triangleIntergationtest() throws IllegalAccessException {
        String message = "wrong number of intersection points";
        Vector vTo = new Vector(0, 0, -1);
        Vector vUp = new Vector(0, 1, 0);
        int nX = 3;
        int nY = 3;
        /*TCO1 1 intersection points */
        Camera camera = new Camera(new Point(0, 0, 0.5), vTo, vUp);
        camera.setVPSize(nX, nY);
        camera.setVPDistance(1);
        assertEquals(1,cameraIntersection(new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2)),camera,nX,nY ),message);
        /*TCO2 2 intersection points */
        Camera camera1 = new Camera(ZERO_POINT, vTo, vUp);
        camera1.setVPSize(nX, nY);
        camera1.setVPDistance(1);
        assertEquals(2,cameraIntersection(new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2)),camera1,nX,nY ),message);



    }

}
