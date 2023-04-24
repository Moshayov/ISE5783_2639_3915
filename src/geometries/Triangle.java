package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * A class representing a triangle in a 3D space.
 */
public class Triangle extends Polygon{
    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Triangle(Point p0, Point p1, Point p2) {
        super(p0, p1, p2);
    }
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}
