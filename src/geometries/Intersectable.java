package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Finds the intersection points of the given ray with this geometrical object.
 *
 * @param ray the ray that intersects with this object.
 * @return a List of Point objects representing the intersection points of the ray and the object.
 */
public interface Intersectable {
    List<Point> findIntersectionPoints(Ray ray);
}
