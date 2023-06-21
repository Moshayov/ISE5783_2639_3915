package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


public abstract class Intersectable {

    /**
     * Finds the intersection points between a given ray and the geometry.
     *
     * @param ray The ray for which intersection points are to be found.
     * @return A list of intersection points between the ray and the geometry.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * A protected abstract method that should be implemented by subclasses to find
     * the intersections between the given ray and the geometry.
     *
     * @param ray the ray for which to find the intersections
     * @return a list of GeoPoints representing the intersections between the ray and the geometry
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Finds the intersections between the given ray and the geometry by calling the
     * abstract findGeoIntersectionsHelper() method implemented by subclasses.
     *
     * @param ray the ray for which to find the intersections
     * @return a list of GeoPoints representing the intersections between the ray and the geometry
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    public static class GeoPoint {
        /*The geometry object associated with the intersection point.*/
        public Geometry geometry;
        /**
         * The intersection point in three-dimensional space.
         */
        public Point point;

        /**
         * Constructs a new GeoPoint object with the given geometry and point.
         *
         * @param geometry The geometry object associated with the intersection point.
         * @param point    The intersection point in three-dimensional space.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Indicates whether some other object is "equal to" this GeoPoint.
         *
         * @param o the reference object with which to compare
         * @return {@code true} if this GeoPoint is the same as the object argument; {@code false} otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        /**
         * Returns a string representation of the GeoPoint object.
         *
         * @return a string representation of the GeoPoint object
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

    }
}
