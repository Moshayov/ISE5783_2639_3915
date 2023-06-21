package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;

    /**
     * Constructs a new instance of the RayTracerBasic class with the specified scene.
     *
     * @param scene the scene to be rendered by the ray tracer
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     * @param ray the ray to trace the scene with
     * @return color
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersectionPoints = this.scene.geometries.findGeoIntersectionsHelper(ray);
        if (intersectionPoints == null)
            return this.scene.background;
        GeoPoint closestGeoPoint = ray.findClosestGeoPoint(intersectionPoints);
        return calcColor(closestGeoPoint, ray);
    }

    /**
     * Calculates the color of the specified intersection point by combining the ambient light intensity and the local effects.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray that intersected with the geometry
     * @return the calculated color at the intersection point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.getAmbientLight().getIntensity()
                .add(calcLocalEffects(geoPoint, ray));
    }

    /**
     * Calculates the local effects (diffuse and specular) at the specified intersection point.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray that intersected with the geometry
     * @return the color representing the local effects at the intersection point
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color color = geoPoint.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);

        double nv = alignZero(n.dotProdouct(v));

        // This is a check to see if the ray is hitting the geometry from the inside.
        if (nv == 0)
            return color;

        Material material = geoPoint.geometry.getMaterial();

        // Calculates the color of a point on a surface,
        // by adding the emission of the surface to the sum of
        // the diffuse and specular colors of the surface
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProdouct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(geoPoint.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }

        return color;
    }

    /**
     * Calculates the diffuse component of the material at the intersection point.
     *
     * @param material the material of the intersected geometry
     * @param nl       the dot product between the normal and the light vector
     * @return the calculated diffuse component as a Double3 object
     */
    private Double3 calcDiffusive(Material material, double nl) {
        nl = Math.abs(nl);
        return material.getKd().scale(nl);
    }

    /**
     * Calculates the specular component of the material at the intersection point.
     *
     * @param material the material of the intersected geometry
     * @param n        the normal vector at the intersection point
     * @param l        the light vector
     * @param nl       the dot product between the normal and the light vector
     * @param v        the view vector
     * @return the calculated specular component as a Double3 object
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must be not zero!
        double minusVR = -alignZero(r.dotProdouct(v));
        if (minusVR <= 0)
            return Double3.ZERO; // view from direction opposite to r vector
        return material.getKs().scale(Math.pow(minusVR, material.nShines));
    }
    private boolean unshaded(LightSource light,GeoPoint gp, Vector l, Vector n){
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections.isEmpty())
            return true;
        for(var intersec :intersections){
            if(intersec.point.distance(point)<light.getDistance(intersec.point))
                return false;
        }
        return true;
    }
}
