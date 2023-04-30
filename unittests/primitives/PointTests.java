package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTests {
    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(4, 5, 6);
        Point p2 = new Point(1, 2, 3);
        assertEquals(p1.subtract(p2), new Vector(3, 3, 3), "Subtract()-point, doesn't work well");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Point p1 = new Point(4, 5, 6);
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(p1.add(v1), new Point(5, 7, 9), "Add()-point, doesn't work well");

    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(4, 5, 6);
        Point p2 = new Point(1, 2, 3);
        assertEquals(p1.distanceSquared(p2), 27, "DistanceSquared()-point, doesn't work well");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        Point p1 = new Point(4, 5, 6);
        Point p2 = new Point(1, 2, 3);
        assertEquals(p1.distance(p2), Math.sqrt(27), "Distance()-point, doesn't work well");
    }
}