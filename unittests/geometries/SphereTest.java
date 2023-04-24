package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sp=new Sphere(new Point(1,2,3),1d);
        assertEquals( new Vector(1/Math.sqrt(3), 1/Math.sqrt(3), 1/Math.sqrt(3)), sp.getNormal(new Point(2, 3, 4)),"Bad normal to sphere");
    }
}