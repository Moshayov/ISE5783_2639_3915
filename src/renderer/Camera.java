package renderer;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    private Point location;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double height;
    private double width;
    private double distance;

    public Camera(Point location, Vector vTo, Vector vUp) throws IllegalAccessException {
        this.location = location;
        if (!isZero(vTo.dotProdouct(vUp))) {
            throw new IllegalArgumentException("The vectors vTo and vUp are not orthogonal");
        }
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    public Point getLocation() {
        return location;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance=distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        //image center
        Point pc = location.add(vTo.scale(distance));
        //ratio pixel width & height
        double  ry = height/nY;
        double  rx = width/nX;
        //pixe[i,j] center
       double yi = -(i- (double) (nY - 1) /2)*ry;
       double xj =  (j- (double) (nX - 1) /2)*rx;
       Point pIJ =  pc;
       if (xj!=0)
           pIJ=pIJ.add(vRight.scale(xj));
        if (yi!=0)
            pIJ=pIJ.add(vUp.scale(yi));
       Vector vij = pIJ.subtract(location);
       return new Ray(location,vij);
    }
}
