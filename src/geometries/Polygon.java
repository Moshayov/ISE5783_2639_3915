package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected final List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected final Plane plane;
    private final int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by
     *                 edge path
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
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size = vertices.length;

        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3) return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProdouct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProdouct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProdouct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * Returns the normal vector to the plane containing the triangle.
     *
     * @param point the point on the triangle (not used in this implementation)
     * @return the normal vector to the plane containing the triangle
     */
    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }


    /*
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (plane.findGeoIntersectionsHelper(ray) == null) {
            // If there is no intersection with the plane of the triangle, return null
            return null;
        }

        // Calculate the intersection point with the plane
        GeoPoint p = plane.findGeoIntersectionsHelper(ray).get(0);

        // Perform additional calculations to determine if the intersection point lies within the triangle
        ArrayList<Vector> vectors = new ArrayList<>();
        ArrayList<Vector> normals = new ArrayList<>();
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();

        // Calculate vectors from p0 to all the vertices of the triangle
        for (Point ver : vertices) {
            vectors.add(ver.subtract(p0));
        }

        // Calculate normals of the triangle edges
        for (int j = 0; j < vectors.size() - 1; j++) {
            normals.add(vectors.get(j).crossProduct(vectors.get(j + 1)).normalize());
        }
        normals.add(vectors.get(vectors.size() - 1).crossProduct(vectors.get(0)).normalize());

        double a = dir.dotProdouct(normals.get(0));
        double b = 0;
        for (int j = 1; j < normals.size(); j++) {
            // Check if all the normals have the same sign
            b = dir.dotProdouct(normals.get(j));
            if (a * b <= 0)
                return null;
        }

        return List.of(p);
    }
*/
    /*
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

        List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray);

        if (planeIntersections == null || !isRayOnPolygon(ray)) return null;

        var result = new LinkedList<GeoPoint>();
        result.add(new GeoPoint(this, planeIntersections.get(0).point));

        return result;
    }
    /**
     * @param ray The ray to check if the intersection is on polygon.
     * @return <b>True</b> if on polygon, <b>false</b> otherwise.
     */
    public boolean isRayOnPolygon(Ray ray) {
        Vector v1, v2;
        v1 = vertices.get(0).subtract(ray.getP0());
        v2 = vertices.get(1).subtract(ray.getP0());

        double prevN = ray.getDir().dotProdouct((v1.crossProduct(v2)).normalize());
        double curN;
        if (alignZero(prevN) == 0) return false;

        for (int i = 1; i < vertices.size(); i++) {
            v1 = v2;
            v2 = vertices.get((i + 1) % vertices.size()).subtract(ray.getP0());
            curN = ray.getDir().dotProdouct((v1.crossProduct(v2)).normalize());
            if (alignZero(curN) == 0 || alignZero(curN * prevN) < 0)
                return false;
            prevN = curN;
        }
        return true;
    }

}
