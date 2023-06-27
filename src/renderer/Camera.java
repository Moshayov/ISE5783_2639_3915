package renderer;
import java.util.*;
import geometries.Plane;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;
import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Camera class represents a camera in a rendering system.
 * <p>
 * It defines the location and orientation of the camera, as well
 * <p>
 * as the view plane size and distance from the camera.
 */
public class Camera {
    private int threadsCount= 3;
    private final int maxLevelAdaptiveSS = 3;    //maximum level of recursion for adaptive supersampling
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
    private final Point location;//the starting point
    private final Vector vTo;
    private final Vector vUp;
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
     */
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
     * Sets the vRight vector of the view plane.
     *
     * @param vRight  The vRight vector the view plane.
     * @return The current Camera object.
     */
    public Camera setvRight(Vector vRight) {
        this.vRight = vRight;
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
    /**
     * Sets whether anti_aliasing is enabled for the camera.
     * Anti_aliasing smooths out the edges of rendered objects,
     * reducing jaggedness and improving visual quality.
     *
     * @param antiAliasing {@code true} to enable anti-aliasing, {@code false} otherwise.
     * @return The current Camera object.
     */
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
        if (isAntiAliasing) {
            if (_N == 0 || _M == 0)
                throw new MissingResourceException("You need to set the n*m value for the rays launching", RayTracerBasic.class.getName(), "");

            List<Ray> rays = constructRaysGridFromRay(nX, nY, _N, _M, ray);
            Color sum = Color.BLACK;
            for (Ray rayy : rays) {
                sum = sum.add(rayTracer.traceRay(rayy));
            }
            return sum.reduce(rays.size());
        }
        return rayTracer.traceRay(ray);
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

    /**
     * Constructs a grid of rays from a given ray, representing a pixel on the camera's view.
     * The grid is defined by the number of rows (nX), number of columns (nY), and the number of rays to launch in each pixel (n, m).
     *
     * @param nX      The number of rows in the grid.
     * @param nY      The number of columns in the grid.
     * @param n       The number of rays to launch vertically in each pixel.
     * @param m       The number of rays to launch horizontally in each pixel.
     * @param ray     The original ray representing the center of the pixel.
     * @return A list of rays representing the constructed grid in the pixel.
     */
    public List<Ray> constructRaysGridFromRay(int nX, int nY, int n, int m, Ray ray) {

        Point p0 = ray.getPoint(distance); //center of the pixel
        List<Ray> myRays = new LinkedList<>(); //to save all the rays

        double pixelHeight = alignZero(height / nY);
        double pixelHWidth = alignZero(width / nX);

        //We call the function constructRayThroughPixel like we used to but this time we launch m * n ray in the same pixel

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                myRays.add(constructRay(m, n, j, i, pixelHeight, pixelHWidth, p0));
            }
        }

        return myRays;
    }
    /**
     * Constructs a ray based on the given parameters.
     *
     * @param m         The number of rays horizontally in the pixel.
     * @param n         The number of rays vertically in the pixel.
     * @param j         The horizontal position of the ray within the pixel.
     * @param i         The vertical position of the ray within the pixel.
     * @param pixelH    The height of a pixel.
     * @param pixelW    The width of a pixel.
     * @param pc        The center point of the pixel.
     * @return A ray representing the constructed ray within the pixel.
     */
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

        //return ray to the center of the pixel
        return new Ray(location, vIJ);

    }
    /**
     * Casts a ray of beams using adaptive super sampling
     * @param j col
     * @param i row
     * @return average colour of pixel
     */

    private Color castBeamAdaptiveSuperSampling(int j, int i) {
        Ray center = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
        Color centerColor = rayTracer.traceRay(center);
        return calcAdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), j, i, maxLevelAdaptiveSS, centerColor);
    }
    /**
     * Calculates the color using adaptive super-sampling technique for a given pixel.
     *
     * @param nX           The number of rays horizontally in the pixel.
     * @param nY           The number of rays vertically in the pixel.
     * @param j            The horizontal position of the pixel.
     * @param i            The vertical position of the pixel.
     * @param maxLevel     The maximum level of recursion for super-sampling.
     * @param centerColor  The color at the center of the pixel.
     * @return The calculated color using adaptive super-sampling.
     */
    private Color calcAdaptiveSuperSampling(int nX, int nY, int j, int i, int maxLevel, Color centerColor) {
        if (maxLevel <= 0) {
            return centerColor;
        }
        Color color = centerColor;
        // divide pixel into 4 mini-pixels
        LinkedList<Ray> beam = (LinkedList<Ray>) Arrays.asList(constructRay(2 * nX, 2 * nY, 2 * j, 2 * i),
                constructRay(2 * nX, 2 * nY, 2 * j, 2 * i + 1),
                constructRay(2 * nX, 2 * nY, 2 * j + 1, 2 * i),
                constructRay(2 * nX, 2 * nY, 2 * j + 1, 2 * i + 1));
        for (int ray = 0; ray < 4; ray++) {
            Color currentColor = rayTracer.traceRay(beam.get(ray));
            if (!currentColor.equals(centerColor))
                currentColor = calcAdaptiveSuperSampling(2 * nX, 2 * nY,
                        2 * j + ray / 2, 2 * i + ray % 2, (maxLevel - 1), currentColor);
            color = color.add(currentColor);
        }
        return color.reduce(5);
    }

    /**
     * renders image using multithreading and adaptive supersampling
     *
     * @return this using builder pattern
     */
    public Camera renderImageMultiThreading_AdaptSS() {
        Pixel.initialize(imageWriter.getNx(),imageWriter.getNy(), 60);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                    imageWriter.writePixel(pixel.col, pixel.row, castBeamAdaptiveSuperSampling(pixel.col, pixel.row));
            }).start();
        }
        Pixel.waitToFinish();
        return this;
    }



}