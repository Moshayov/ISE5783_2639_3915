package primitives;
import geometries.Intersectable;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * Ray class represents a ray in the three-dimensional space, represented by a starting point
 * and a direction vector.
 */
public class Ray {
    /**
     * The starting point of the ray.
     */
    final private Point P0;
    /**
     * The normalized direction vector of the ray.
     */
    final private Vector dir;
    /**
     * Returns the starting point of the ray.
     * @return the starting point of the ray
     */
    public Point getP0() {
        return P0;
    }
    /**
     * Constructs a new Ray object with a given starting point and direction vector.
     * @param p0 the starting point of the ray
     * @param d the direction vector of the ray
     */
    public Ray(Point p0, Vector d){
        P0 = p0;
        dir = d.normalize();
    }
    /**
     * Returns the normalized direction vector of the ray.
     * @return the normalized direction vector of the ray
     */
    public Vector getDir() {
        return dir;
    }
    /**
     * Returns a string representation of the Ray object.
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
     * @param _object the object to compare this Ray object against
     * @return true if the Ray objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object _object) {
        if (this == _object) return true;
        if (!(_object instanceof Ray ray)) return false;
        return P0.equals(ray.P0) && dir.equals(ray.dir);
    }

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
    public GeoPoint findClosestGeoPoint(List<GeoPoint>pointList){
        if (pointList.size() == 0)
            return null;
        double close = P0.distanceSquared(pointList.get(0).point);
        int index = 0;
        for (int i = 1; i < pointList.size(); i++) {
            if (P0.distanceSquared(pointList.get(i).point) < close) {
                close = P0.distanceSquared(pointList.get(i).point);
                index = i;
            }
        }
        return pointList.get(index);
    }
}
