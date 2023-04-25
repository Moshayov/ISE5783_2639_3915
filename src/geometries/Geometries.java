package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

public class Geometries implements Intersectable{
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
     * @param geometry
     */
    public Geometries(Intersectable... geometry){

         this.geometries = new LinkedList<>(List.of(geometry));
    }
    public void add(Intersectable... geometry){
        geometries.addAll(List.of(geometry));
    }
    /**
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntsersections(Ray ray) {
        boolean flage = false;
        for (Intersectable shape:geometries) {
            if( shape.findIntsersections(ray)!=null) {
                flage=true;
                break;
            }
        }
        if(flage) {
            List<Point> Intersect_Point = new LinkedList();
            for (Intersectable shape : geometries) {
                if( shape.findIntsersections(ray)!=null)
                {
                    for (Point p:shape.findIntsersections(ray)) {
                        Intersect_Point.add(p);
                    }
                }
              return Intersect_Point;
            }
        }
        return null;
    }
    }

