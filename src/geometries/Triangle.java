package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class representing a triangle in a 3D space.
 */
public class Triangle extends Polygon {


    /**
     * Triangle constructor based on vertices list. The list must be ordered by edge
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        /*If the ray does not have a point of intersection with the plane,
         *then neither does it with the triangle, so we will not continue to check and return NULL.
         */
        List<GeoPoint> plane_intersection = plane.findGeoIntersectionsHelper(ray);
        if (plane_intersection == null)
            return null;
        /*We found the three sides of the triangle with their help we found their normal,
         and checked if they all have the same sign if so there is a point if not there is no point of
         intersection and we will return NULL
         */
        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);
        Point point3 = vertices.get(2);

        Vector v1 = point1.subtract(p0);
        Vector v2 = point2.subtract(p0);
        Vector v3 = point3.subtract(p0);

        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        double f1 = v.dotProdouct(n1);
        double f2 = v.dotProdouct(n2);
        double f3 = v.dotProdouct(n3);

        if (f1 > 0 && f2 > 0 && f3 > 0 || f1 < 0 && f2 < 0 && f3 < 0) {
            Point p = plane_intersection.get(0).point;
            return List.of(new GeoPoint(this, p));
        }
        return null;
    }
}
