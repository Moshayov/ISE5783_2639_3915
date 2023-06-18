package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

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

    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.getAmbientLight().getIntensity()
                .add(calcLocalEffects(geoPoint, ray));
    }

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
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(geoPoint.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }

        return color;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        nl = Math.abs(nl);
        return material.kD.scale(nl);
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must be not zero!
        double minusVR = -alignZero(r.dotProdouct(v));
        if (minusVR <= 0)
            return Double3.ZERO; // view from direction opposite to r vector
        return material.kS.scale(Math.pow(minusVR, material.nShines));
    }


}
