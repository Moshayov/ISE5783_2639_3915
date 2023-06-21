package geometries;

import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    List<Intersectable> geometries;

    /**
     * empty constructor
     */
    public Geometries() {
        this.geometries = new LinkedList<>();
    }

    /**
     * constructor with param. get list of geometries
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>(Arrays.asList(geometries));
    }

    /**
     * Adds one or more Intersectable objects to the list of geometries.
     *
     * @param geometries the Intersectable objects to add
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * Helper method to find the intersections between the given ray and the geometries in the scene.
     *
     * @param ray the ray for which to find the intersections
     * @return a list of GeoPoints representing the intersections between the ray and the geometries,
     *         or {@code null} if no intersections are found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> pointList = null;
        for (Intersectable item : geometries) {
            List<GeoPoint> itemPointList = item.findGeoIntersections(ray);
            if (itemPointList != null) {
                if (pointList == null) {
                    pointList = new LinkedList<GeoPoint>();
                }
                pointList.addAll(itemPointList);
            }
        }
        return pointList;
    }
}

