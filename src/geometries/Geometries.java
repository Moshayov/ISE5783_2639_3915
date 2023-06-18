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

    public void add(Intersectable... geometries) {
        this.geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * @param ray -the ray of the camara
     * @return point list
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

