package primitives;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTests {
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(2,3,4);
        assertEquals(v1.add(v2), new Vector(3,5,7),"AddVector() -vector doesn't work well");
    }
    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        Vector v1=new Vector(1,-2,3);
        assertEquals(v1.scale(-2), new Vector(-2, 4, -6),"Scale()-vector doesn't work well");
    }
    /**
     * Test method for {@link primitives.Vector#dotProdouct(primitives.Vector)}.
     */
    @Test
    void testDotProdouct() {
        Vector v1=new Vector(-1,2,-3);
        Vector v2=new Vector(2,-3,-4);
        assertEquals(v1.dotProdouct(v2), 4,"DotProduct()-vector doesn't work well");
    }
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProdouct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProdouct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");


    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        Vector v1=new Vector(-1,2,-3);
        assertEquals(v1.lengthSquared(), 14,"LengthSquared()-vector doesn't work well");
    }


    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        Vector v1=new Vector(-1,2,-3);
        assertEquals(v1.length(), Math.sqrt(14),"Length()-vector doesn't work well");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(u.length() - 1),
                "ERROR: the normalized vector is not a unit vector");
        assertThrows(IllegalArgumentException.class,
                () ->v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");
        assertFalse(v.dotProdouct(u) < 0,
                "ERROR: the normalized vector is opposite to the original one");
    }
}