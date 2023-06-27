package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;


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

    /**
     * Returns the normal vector to the geometry at the specified point.
     * Delegates the calculation to the superclass implementation.
     *
     * @param point the point on the geometry for which to calculate the normal vector
     * @return the normal vector to the geometry at the specified point
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    /**
     * Helper method to find the intersections between the given ray and the triangle geometry.
     * The method first checks if there is an intersection with the plane of the triangle,
     * and then performs additional calculations to determine if the intersection point lies within the triangle.
     *
     * @param ray the ray for which to find the intersections
     * @return a list of GeoPoints representing the intersections between the ray and the triangle geometry,
     *         or {@code null} if no intersections are found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> plane_intersection = plane.findGeoIntersectionsHelper(ray);
        if (plane_intersection == null)
            return null;

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

        if ((f1 > 0 && f2 > 0 && f3 > 0) || (f1 < 0 && f2 < 0 && f3 < 0)) {
            Point p = plane_intersection.get(0).point;
            return List.of(new GeoPoint(this, p));
        }

        return null;
    }

}
