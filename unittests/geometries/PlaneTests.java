package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Vector;
import  geometries.Plane;
class PlaneTests {

    @Test
    void testGetNormal() {
        //TC01: test getNormal without param.
        Plane p_instance = new Plane(new Point(0.0, 1.0, 1.0), new Point(0.0, 0.0, 1.0), new Point(0.0, 1.0, 0.0));
        //Point3D point_test = new Point3D()
        Vector expected = new Vector(1.0,0.0,0.0);
        assertEquals(expected ,(p_instance.getNormal()), "TC01: ");//((p_instance.getNormal()).dotProduct(expected)
        //assertTrue("TC01: ", ((p_instance.getNormal()).dotProduct(expected)) == 0);
    }
    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     * ?
     */
    @Test
    void testTestGetNormal() {
        Plane pl = new Plane(new Point(1, 0, 0), new Point(1, 1, 0), new Point(0, 1, 0));
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 0,1)), "wrong plane normal");
    }
}