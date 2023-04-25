package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
    @Override
    public List<Point> findIntsersections(Ray ray) {
        /*If the ray does not have a point of intersection with the plane,
         *then neither does it with the triangle, so we will not continue to check and return NULL.
         */
        List<Point> plane_intersection = plane.findIntsersections(ray);
        Vector v = ray.getDir();
        if (plane_intersection ==null)
            return null;
        /*We found the three sides of the triangle with their help we found their normal,
         and checked if they all have the same sign if so there is a point if not there is no point of
         intersection and we will return NULL
         */
        Vector v1 = vertices.get(1).Subtract(vertices.get(0));
        Vector v2 = vertices.get(2).Subtract(vertices.get(0));
        Vector v3 = vertices.get(3).Subtract(vertices.get(0));
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();
        if(n1.dotProdouct(v)>0 &&n2.dotProdouct(v) >0 &&n3.dotProdouct(v)>0||n1.dotProdouct(v)<0 &&n2.dotProdouct(v) <0 &&n3.dotProdouct(v)<0)
            return plane_intersection;
        return null;
    }
}
