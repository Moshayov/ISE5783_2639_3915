package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * The Camera class represents a camera in a rendering system.
 * <p>
 * It defines the location and orientation of the camera, as well
 * <p>
 * as the view plane size and distance from the camera.
 */
public class Camera {
    private final Point location;
    private final Vector vTo;
    private final Vector vUp;
    private final Vector vRight;
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
        //normellize?
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

        //image center
        Point pc = location.add(vTo.scale(distance));

        //ratio pixel width & height
        double ry = height / nY;
        double rx = width / nX;

        //pixe[i,j] center
        double yi = -(i - (double) (nY - 1) / 2) * ry;
        double xj = (j - (double) (nX - 1) / 2) * rx;

        Point pIJ = pc;
        if (!isZero(xj))
            pIJ = pIJ.add(vRight.scale(xj));
        if (!isZero(yi))
            pIJ = pIJ.add(vUp.scale(yi));
        Vector vij = pIJ.subtract(location);
        return new Ray(location, vij);
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

    private Color castRay(int nX, int nY, int j, int i) {
        Ray ray = this.constructRay(nX, nY, j, i);
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

}
