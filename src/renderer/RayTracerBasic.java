package renderer;

import geometries.Intersectable;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * @param ray the ray to trace the scene with
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Intersectable.GeoPoint> intersectionPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionPoints == null)
            return this.scene.background;
        GeoPoint geoPoint = ray.findClosestGeoPoint(intersectionPoints);
        return calcColor(geoPoint,ray);
    }
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(geoPoint, ray));
    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
       return null;

    }


}
