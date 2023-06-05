package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


public abstract class  Intersectable {
    /**

     Finds the intersection points between a given ray and the geometry.
     @param ray The ray for which intersection points are to be found.
     @return A list of intersection points between the ray and the geometry.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }
    protected abstract List<GeoPoint>findGeoIntersectionsHelper(Ray ray);
    public final List<GeoPoint> findGeoIntersections(Ray ray){
       return findGeoIntersectionsHelper(ray);
    }

    public static class GeoPoint {
        /*The geometry object associated with the intersection point.*/
        public Geometry geometry;
        /**
         The intersection point in three-dimensional space.
         */
        public Point point;
        /**
         Constructs a new GeoPoint object with the given geometry and point.
         @param geometry The geometry object associated with the intersection point.
         @param point The intersection point in three-dimensional space.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
