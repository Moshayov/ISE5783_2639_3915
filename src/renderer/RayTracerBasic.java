package renderer;

import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {


    //Recursion stopping conditions of transparencies/reflections
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * The initial color coefficient for recursion.
     */
    private static final Double3 INIT_CALC_COLOR_K = Double3.ONE;

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
    public Color traceRay(Ray ray ,boolean isSS, int depth) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null)
            //ray did not intersect any geometrical object
            return scene.getBackground();
        return calcColor(closestPoint, ray, isSS, depth);
    }

    /**

     Calculates the color at a given geometric point based on the specified ray.
     @param geoPoint The geometric point at which the color is to be calculated.
     @param ray The ray used to calculate the color.
     @return The calculated color at the specified geometric point.
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, Boolean isSS, int depth) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INIT_CALC_COLOR_K, isSS, depth)
                .add(scene.ambientLight.getIntensity());
    }
    /**

     Calculates the color at a given geometric point based on the specified ray and additional parameters.
     @param geoPoint The geometric point at which the color is to be calculated.
     @param ray The ray used to calculate the color.
     @param level The current recursion level.
     @param k The additional parameters used in the calculation.
     @return The calculated color at the specified geometric point.
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k,Boolean isSS, int depth) {
        Color color = geoPoint.geometry.getEmission()
                .add(calcLocalEffects(geoPoint, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k, isSS, depth));
    }
    /**

     Calculates the local effects (diffuse and specular colors) at a given geometric point based on the specified ray and additional parameters.

     @param geoPoint The geometric point at which the local effects are to be calculated.

     @param ray The ray used to calculate the local effects.

     @param k The additional parameters used in the calculation.

     @return The calculated color representing the local effects at the specified geometric point.
     */

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray,Double3 k) {
        Color color = geoPoint.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double vn = alignZero(v.dotProdouct(n));

        // This is a check to see if the ray is hitting the geometry from the inside.
        if (vn == 0) return Color.BLACK;

        Material material = geoPoint.geometry.getMaterial();

        // Calculates the color of a point on a surface,
        // by adding the emission of the surface to the sum of
        // the diffuse and specular colors of the surface
        //
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProdouct(l));
            if (alignZero(nl * vn)>0) { // sign(nl) == sign(nv)
                //check if a light source is
                //blocked by other objects –
                //then we know it is in
                //shadow.
                Double3 ktr = transparency(geoPoint,lightSource,l,n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K))
                 {
                    Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
                     color = color.add(iL.scale(calcDiffusive(material, nl) //
                             .add(calcSpecular(material, n, l, nl, v))));
                }
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

    /**

     Calculates the transparency coefficient (ktr) at a given geometric point for a specific light source.

     @param geoPoint The geometric point at which the transparency coefficient is to be calculated.

     @param ls The light source for which the transparency coefficient is calculated.

     @param l The direction vector from the geometric point towards the light source.

     @param n The normal vector at the geometric point.

     @return The transparency coefficient (ktr) at the specified geometric point for the given light source.
     */
    private  Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n){
        Ray lightRay = new Ray(geoPoint.point, l.scale(-1),n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        Double3 ktr = Double3.ONE;
        if (intersections==null) return ktr;
        double lightDistance = ls.getDistance(geoPoint.point);
        for(var intersection :intersections){
            if(alignZero(intersection.point.distance(geoPoint.point) - lightDistance) <= 0)
                ktr = ktr.product(intersection.geometry.getMaterial().Kt);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
        }
        return ktr;
    }
    /**

     Calculates the reflection ray at a given geometric point for a specific incident ray and normal vector.
     @param geoPoint The geometric point at which the reflection ray is calculated.
     @param l The incident ray direction vector.
     @param n The surface normal vector.
     @return The reflection ray at the specified geometric point.
     */
    private  Ray  Calculation_reflection(GeoPoint geoPoint, Vector l, Vector n){
         Vector reflected = l.subtract(n.scale(2*l.dotProdouct(n)));
         return new Ray(geoPoint.point,reflected,n);
    }
    /**

     Calculates the refraction ray at a given geometric point for a specific incident ray and normal vector.
     @param geoPoint The geometric point at which the refraction ray is calculated.
     @param l The incident ray direction vector.
     @param n The surface normal vector.
     @return The refraction ray at the specified geometric point.
     */
    private Ray Calculation_refraction(GeoPoint geoPoint, Vector l, Vector n){
        return new Ray(geoPoint.point,l,n);
    }

    /**
     * Find the closest intersection point with a ray.
     *
     * @param ray The ray to checks intersections with.
     * @return The closest intersection point with the ray.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }
    //helper function for calc color
    /**

     Calculates the global effects (reflection and refraction colors) at a given geometric point based on the specified ray, recursion level, and additional parameters.
     @param geoPoint The geometric point at which the global effects are to be calculated.
     @param ray The ray used to calculate the global effects.
     param level The current recursion level.
     @param k The additional parameters used in the calculation.
     @return The calculated color representing the global effects at the specified geometric point.
    */
    private Color calcGlobalEffects(GeoPoint geoPoint,Ray ray, int level, Double3 k, boolean isSS, int depth) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Material material = geoPoint.geometry.getMaterial();
        Ray reflectedRay = Calculation_reflection(geoPoint, v, n);
        Ray refractedRay = Calculation_refraction(geoPoint, v, n);
        return calcGlobalEffect(level, material.Kr, k, reflectedRay,isSS,depth)
                .add(calcGlobalEffect(level, material.Kt, k, refractedRay,isSS,depth));
    }

    /**
     * function calculates global effects of color on point
     *
     * @param level level of recursion
     * @param kx    kx value of material
     * @param k     k value until now
     * @param ray   ray that intersects
     * @return color
    */
    private Color calcGlobalEffect(int level, Double3 kx, Double3 k, Ray ray, boolean isSS, int depth) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint reflectedPoint = findClosestIntersection(ray);
        return (reflectedPoint == null ? scene.background : calcColor(reflectedPoint, ray, level - 1, kkx,isSS,depth)).scale(kx);
    }



}
