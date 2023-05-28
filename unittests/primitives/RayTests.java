package primitives;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTests {

    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(50,50,-50),new Vector(50,50,-50).normalize());
        List<Point>pointList= Arrays.asList(
                new Point(-100, 0, -100),
                new Point(0, 100, -100),
                new Point(-100, 100, -100)
        );
        //TC01  Checking that the second point in the list is the closest point
        assertEquals(ray.findClosestPoint(pointList),pointList.get(1),"didnt find the right closet point");
        //TC02 Checking for an empty list, the expected result is null
        List<Point> points =  new LinkedList<>(); ;
        assertNull(ray.findClosestPoint(points),"The method returned a value other than null which is the expected result for an empty list");
        //TC03  Checking that the first point in the list is the closest point
        assertEquals(new Ray(new Point(-50,50,50),new Vector(-50,50,50).normalize()).findClosestPoint(pointList),pointList.get(0),"didnt find the right closet point");
        //TC04  Checking that the first point in the list is the closest point
        assertEquals(new Ray(new Point(-70,70,-70),new Vector(-70,70,-70).normalize()).findClosestPoint(pointList),pointList.get(2),"didnt find the right closet point");

    }
}