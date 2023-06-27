package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

/**
 * Ray class represents a ray in the three-dimensional space, represented by a starting point
 * and a direction vector.
 */
public class Ray {

    private static final double DELTA = 0.1;
    /**
     * The starting point of the ray.
     */
    final private Point P0;
    /**
     * The normalized direction vector of the ray.
     */
    final private Vector dir;

    /**
     * Constructs a new Ray object with a given starting point and direction vector.
     *
     * @param p0 the starting point of the ray
     * @param d  the direction vector of the ray
     */
    public Ray(Point p0, Vector d) {
        P0 = p0;
        dir = d.normalize();
    }


    /**
     * Constructor that moves the ray by DELTA
     *
     * @param p0 point
     * @param direction direction (must be normalized)
     * @param normal normal
     */
    public Ray(Point p0, Vector direction, Vector normal) {
        Vector delta = normal.scale(normal.dotProdouct(direction) > 0 ? DELTA : - DELTA);
        this.P0 = p0.add(delta);
        this.dir = direction;
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return the starting point of the ray
     */
    public Point getP0() {
        return P0;
    }

    /**
     * Returns the  direction vector of the ray.
     *
     * @return the  direction vector of the ray
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Returns a string representation of the Ray object.
     *
     * @return a string representation of the Ray object
     */
    @Override
    public String toString() {
        return "Ray{" +
                "P0=" + P0 +
                ", dir=" + dir +
                '}';
    }
    /**
     * Compares this Ray object to the specified object. The result is true if and only if
     * the argument is not null and is a Ray object that has the same starting point and
     * direction vector as this Ray object.
     *
     * @param o the object to compare this Ray object against
     * @return true if the Ray objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return P0.equals(ray.P0) && dir.equals(ray.dir);
    }
    /**
     * Returns a point on the line at a specified distance from the starting point.
     *
     * @param distance The distance from the starting point along the line
     * @return The point on the line at the specified distance
     */
    public Point getPoint(double distance) {
        return P0.add(dir.scale(distance));
    }

    /**
     * Finds the closest point to a reference point in a given list of points.
     *
     * @param pointList The list of points to search from.
     * @return The closest point to the reference point.
     */
    public Point findClosestPoint(List<Point> pointList) {
        return pointList == null || pointList.isEmpty() ? null
                : findClosestGeoPoint(pointList.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint from the given list of GeoPoints to the current GeoPoint.
     * If the list is empty, returns null.
     *
     * @param pointList the list of GeoPoints to search from
     * @return the closest GeoPoint to the current GeoPoint, or null if the list is empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> pointList) {
        if (pointList.size() == 0)
            return null;
        double closestDistanceSquared = P0.distanceSquared(pointList.get(0).point);
        int index = 0;
        for (int i = 1; i < pointList.size(); i++) {
            double distanceSquared = P0.distanceSquared(pointList.get(i).point);
            if (distanceSquared < closestDistanceSquared) {
                closestDistanceSquared = distanceSquared;
                index = i;
            }
        }
        return pointList.get(index);
    }

}
