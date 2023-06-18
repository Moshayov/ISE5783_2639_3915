package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    List<Intersectable> geometries = null;

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
        this();
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        for (var elem : geometries) {
            this.geometries.add(elem);
        }
    }

    //**
 /*Finds the intersection points between a given ray and the geometries in the scene.
            *
            * @param ray the ray to find intersection points with
 * @return a list of intersection points, or null if no intersections occur
 */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> pointList = null;
        for (Intersectable item : geometries) {
            List<GeoPoint> itemPointList = item.findGeoIntersections(ray);
            if (itemPointList != null) {
                if (pointList == null) {
                    pointList = new LinkedList<>();
                }
                pointList.addAll(itemPointList);
            }
        }
        return pointList;
    }
}

