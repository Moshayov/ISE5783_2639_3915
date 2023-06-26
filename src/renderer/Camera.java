package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;
import java.util.Random;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
//
/**
 * The Camera class represents a camera in a rendering system.
 * <p>
 * It defines the location and orientation of the camera, as well
 * <p>
 * as the view plane size and distance from the camera.
 */
public class Camera {

    /**
     * The depth of adaptive super sampling's recursion
     */
    private int Depth = 3;


    /**
     * turn on - off adaptive super sampling
     */
    private boolean isSS = false;

    public void setSS(boolean SS) {
        isSS = SS;
    }

    /*
     * random variable used for stochastic ray creation
     */
    private final Random r = new Random();

    private int _N = 8;
    private int _M = 8;

    /*
    isAntiAliasing- for anti aliasing
     */
    private boolean isAntiAliasing = false;


    private final Point location;
    private final Vector vTo;
    private final Vector vUp;

    public Camera setvRight(Vector vRight) {
        this.vRight = vRight;
        return this;
    }

    private  Vector vRight;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * Constructs a new Camera object with the specified location,
     * direction vectors vTo and vUp.
     *
     * @param location The location of the camera.
     * @param vTo      The direction vector towards the view plane.
     * @param vUp      The direction vector pointing upwards.
     * @throws IllegalArgumentException if vTo and vUp are not orthogonal.
     *///
    public Camera(Point location, Vector vTo, Vector vUp) {
        this.location = location;
        if (!isZero(vTo.dotProdouct(vUp))) {
            throw new IllegalArgumentException("The vectors vTo and vUp are not orthogonal");
        }
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * Returns the location of the camera.
     *
     * @return The location of the camera.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Returns the direction vector towards the view plane.
     *
     * @return The direction vector towards the view plane.
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Returns the direction vector towards the view plane.
     *
     * @return The direction vector towards the view plane.
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Returns the direction vector pointing to the right.
     *
     * @return The direction vector pointing to the right.
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return The height of the view plane.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return The width of the view plane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the distance between the camera and the view plane.
     *
     * @return The distance between the camera and the view plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the size of the view plane.
     *
     * @param width  The width of the view plane.
     * @param height The height of the view plane.
     * @return The current Camera object.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the distance between the camera and the view plane.
     *
     * @param distance The distance between the camera and the view plane.
     * @return The current Camera object.
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Sets the ImageWriter for the Camera.
     *
     * @param imageWriter the ImageWriter object to set
     * @return the Camera object with the updated ImageWriter
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the RayTracer for the Camera.
     *
     * @param rayTracer the RayTracerBase object to set
     * @return the Camera object with the updated RayTracer
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Constructs a ray for a given pixel coordinates (j, i) on the view plane.
     *
     * @param nX The total number of pixels in the X direction.
     * @param nY The total number of pixels in the Y direction.
     * @param j  The column index of the pixel.
     * @param i  The row index of the pixel.
     * @return The constructed Ray object.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = location.add(vTo.scale(distance)); //Image center

        //Ratio (pixel width & height)
        double Rx = width / nX;
        double Ry = height / nY;

        Point Pij; //Pixel[i,j] center
        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        // Pixel[i,j] is the center
        if (isZero(Xj) && isZero(Yi)) {
            Pij = Pc;
            return new Ray(location, Pij.subtract(location));
        }
        // Pixel[i,j] is in the middle column
        if (isZero(Xj)) {
            Pij = Pc.add(vUp.scale(Yi));
            return new Ray(location, Pij.subtract(location));
        }
        //Pixel[i,j] is in the middle row
        if (isZero(Yi)) {
            Pij = Pc.add(vRight.scale(Xj));
            return new Ray(location, Pij.subtract(location));
        }

        Pij = Pc.add(vRight.scale(Xj).add(vUp.scale(Yi)));
        return new Ray(location, Pij.subtract(location));

    }

    public Camera setAntiAliasing(boolean antiAliasing) {
        this.isAntiAliasing = antiAliasing;
        return this;
    }

    /**
     * Renders the image using the configured ImageWriter and RayTracer.
     * Throws a MissingResourceException if any of the required fields (ImageWriter, RayTracer, width, height, distance) are missing or have invalid values.
     *
     * @throws MissingResourceException if the Camera is missing some required fields
     */
    public Camera renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("Renderer resource not set", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("Renderer resource not set", RayTracerBase.class.getName(), "");
            }
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();

            for (int row = 0; row < nY; row++) {
                for (int col = 0; col < nX; col++) {
                    Color color = castRay(nX, nY, row, col);
                    this.imageWriter.writePixel(row, col, color);
                }
            }

        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not yet initialized" + e.getClassName());
        }
        return this;
    }

    /**
     * Casts a ray from the specified pixel coordinates and retrieves the color at the intersection point.
     *
     * @param nX the number of pixels in the X direction (horizontal resolution)
     * @param nY the number of pixels in the Y direction (vertical resolution)
     * @param j  the pixel's column index
     * @param i  the pixel's row index
     * @return the color at the intersection point of the ray cast from the specified pixel coordinates
     */
    private Color castRay(int nX, int nY, int j, int i) {
        Ray ray = this.constructRay(nX, nY, j, i);
        Ray[][] rays = constructRaysGridFromRay(imageWriter.getNx(), imageWriter.getNy(), _N, _M, ray);
        if (isAntiAliasing) {
            if (_N == 0 || _M == 0)
                throw new MissingResourceException("You need to set the n*m value for the rays launching", RayTracerBase.class.getName(), "");

            if (!isSS) {
                Color sum = Color.BLACK;
                for (Ray[] rayy : rays) {
                    for (Ray rayyy : rayy)
                        sum = sum.add(rayTracer.traceRay(rayyy, isSS, Depth));
                }
                return sum.reduce(_N * _M);
            } else {
                Color lu = rayTracer.traceRay(rays[0][0], isSS, Depth);
                Color ld = rayTracer.traceRay(rays[_N - 1][0], isSS, Depth);
                Color ru = rayTracer.traceRay(rays[0][_M - 1], isSS, Depth);
                Color rd = rayTracer.traceRay(rays[_N - 1][_M - 1], isSS, Depth);
                if (lu.equals(ld) && lu.equals(ru) && lu.equals(rd)) {
                    return lu;
                } else {
                    Color help = helpSuperSampling(rays, lu, ld, ru, rd,
                            0, 0, _N - 1, _M - 1, Depth);
                    return help;
                }
            }
        }
        return rayTracer.traceRay(ray,isSS,Depth);
    }


    /**
     * Prints a grid on the image using the specified color and interval.
     * Throws a MissingResourceException if the ImageWriter is uninitialized.
     *
     * @param color    the color to use for the grid
     * @param interval the interval at which to place the grid lines
     * @throws MissingResourceException if the Camera is missing the ImageWriter
     */
    public void printGrid(Color color, int interval) throws MissingResourceException {
        if (this.imageWriter == null) // the image writer is uninitialized
            throw new MissingResourceException("Camera is missing some fields", "Camera", "imageWriter");
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                if (i % interval == 0 || j % interval == 0)  // color the grid
                    imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Writes the image to the output using the configured ImageWriter.
     * Throws a MissingResourceException if the ImageWriter is uninitialized.
     *
     * @throws MissingResourceException if the Camera is missing the ImageWriter
     */
    public void writeToImage() {
        if (this.imageWriter == null) { // the image writer is uninitialized
            throw new MissingResourceException("Camera is missing some fields", "Camera", "imageWriter");
        }
        imageWriter.writeToImage();
    }

    public Ray[][] constructRaysGridFromRay(int nX, int nY, int n, int m, Ray ray) {

        Point p0 = ray.getPoint(distance); //center of the pixel
        Ray[][] myRays = new Ray[n][m]; //to save all the rays


        double pixelHeight = alignZero(height / nY);
        double pixelHWidth = alignZero(width / nX);

        //We call the function constructRayThroughPixel like we used to but this time we launch m * n ray in the same pixel

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                myRays[i][j]=constructRay(m, n, j, i, pixelHeight, pixelHWidth, p0);
            }
        }

        return myRays;
    }
    private Ray constructRay(int m, int n, double j, double i, double pixelH, double pixelW, Point pc) {

        Point pIJ = pc;

        //Ry = height / nY : height of a pixel
        double rY = pixelH / n;
        //Ry = weight / nX : width of a pixel
        double rX = pixelW / m;
        //xJ is the value of width we need to move from center to get to the point
        //we get to the bottom/top of the pixel and then we move randomly in the pixel to get the point
        double xJ = ((j + r.nextDouble() / (r.nextBoolean() ? 2 : -2)) - ((m - 1) / 2d)) * rX;
        //yI is the value of height we need to move from center to get to the point
        //we get to the side of the pixel and then we move randomly in the pixel to get the point
        double yI = -((i + r.nextDouble() / (r.nextBoolean() ? 2 : -2)) - ((n - 1) / 2d)) * rY;

        if (xJ != 0) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (yI != 0) {
            pIJ = pIJ.add(vUp.scale(yI));
        }

        //get vector from camera p0 to the point
        Vector vIJ = pIJ.subtract(location);
        //hadas
        //return ray to the center of the pixel
        return new Ray(location, vIJ);

    }

    private Color helpSuperSampling(Ray[][] rays, Color lu, Color ld, Color ru, Color rd, int x, int y, int z, int w, int depth) {
        if (depth == 0)
            return lu;
        Color col = Color.BLACK;
        Color mu = rayTracer.traceRay(rays[x][(y + w) / 2], isSS, Depth);
        Color md = rayTracer.traceRay(rays[z][(y + w) / 2], isSS, Depth);
        Color mm = rayTracer.traceRay(rays[(x + z) / 2][(y + w) / 2], isSS, Depth);
        Color lm = rayTracer.traceRay(rays[(x + z) / 2][y], isSS, Depth);
        Color rm = rayTracer.traceRay(rays[(x + z) / 2][w], isSS, Depth);
        if (lu.equals(mu) && lu.equals(mm) && lu.equals(lm))
            col = col.add(lu);
        else
            col = col.add(helpSuperSampling(rays, lu, lm, mu, mm, x, y, (x + z) / 2, (y + w) / 2, depth - 1));
        if (mu.equals(ru) && mu.equals(mm) && mu.equals(rm))
            col = col.add(mu);
        else
            col = col.add(helpSuperSampling(rays, mu, mm, ru, rm, x, (y + w) / 2, (x + z) / 2, w, depth - 1));
        if (lm.equals(mm) && lm.equals(ld) && lm.equals(md))
            col = col.add(lm);
        else
            col = col.add(helpSuperSampling(rays, lm, ld, mm, md, (x + z) / 2, y, z, (y + w) / 2, depth - 1));
        if (mm.equals(rm) && mm.equals(md) && mm.equals(rd))
            col = col.add(mm);
        else
            col = col.add(helpSuperSampling(rays, mm, md, rm, rd, (x + z) / 2, (y + w) / 2, z, w,depth - 1));
        return col.reduce(4);
    }
}
