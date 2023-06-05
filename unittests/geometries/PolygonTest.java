package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

class PolygonTest {

    @Test
    void getNormal() {
// ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to trinagle");

    }

    @Test
    void testFindIntersectionPoints() {
        Polygon poly = new Polygon( new Point(-1,0,1), new Point(1,0,1), new Point(0,2,1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: The point of intersection inside the polygon (1 point)
        Point p = new Point(0,1,1);
        List<Point> result = poly.findIntersections(new Ray(new Point(0,2, 0),
                new Vector(0,-1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p), result, "Ray crosses polygon");
        // TC02: The point of intersection is outside the polygon opposite a side (0 points)
        result = poly.findIntersections(new Ray(new Point(0,2,0),
                new Vector(2,-1,1 )));
        assertNull( result, "Ray's line out of polygon");
        // TC03: The point of intersection is outside the polygon opposite a vertex (0 points)
        result = poly.findIntersections(new Ray(new Point(0,2,1),
                new Vector(0,1, 1)));
        assertNull( result, "Ray's line out of polygon");

        // =============== Boundary Values Tests ==================
        // TC11: The intersection point is on a side (0 points)
        result = poly.findIntersections(new Ray(new Point(0,2,0),
                new Vector(-0.5,-1,1)));
        assertNull( result, "Wrong number of points");
        // TC12: The intersection point is on a vertex (0 points)
        result = poly.findIntersections(new Ray(new Point(0,2,0),
                new Vector(2,-2,1)));
        assertNull( result, "Wrong number of points");
        // TC13: The intersection point is on the continuation of an edge (0 points)
        result = poly.findIntersections(new Ray(new Point(0,2,0),
                new Vector(-1,-2,1)));
        assertNull( result, "Wrong number of points");
    }
}