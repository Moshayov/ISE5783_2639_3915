package primitives;

import java.util.Objects;

/**
 * Represents a 3D point in space.
 */
public class Point {
    public static final Point ZERO = new Point(0, 0, 0);
    final protected Double3 xyz;

    /**
     * Constructs a new point from a `Double3` object.
     *
     * @param double3 the `Double3` object containing the coordinates of the point
     */
   Point(Double3 double3) {
       this.xyz=new Double3(double3.d1,double3.d2,double3.d3);

    }



    /**
     * Constructs a new point from three coordinates.
     *
     * @param d1 the x-coordinate of the point
     * @param d2 the y-coordinate of the point
     * @param d3 the z-coordinate of the point
     */
    public Point(double d1, double d2, double d3) {
        xyz=new Double3(d1, d2, d3);

    }

    /**
     * Computes the vector that points from another point to this point.
     *
     * @param p the other point
     * @return the vector from the other point to this point
     */
    public Vector subtract(Point p) {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Computes the point obtained by adding a vector to this point.
     *
     * @param v the vector to add to this point
     * @return the new point obtained by adding the vector to this point
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Computes the squared distance between this point and another point.
     *
     * @param point2 the other point
     * @return the squared distance between this point and the other point
     */
    public double distanceSquared(Point point2) {
        double dx = xyz.d1 - point2.xyz.d1;
        double dy = xyz.d2 - point2.xyz.d2;
        double dz = xyz.d3 - point2.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Computes the distance between this point and another point.
     *
     * @param p2 the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point p2) {
        double distanceSquared = distanceSquared(p2);
        return Math.sqrt(distanceSquared);
    }

    /**
     * Determines whether this point is equal to another object.
     *
     * @param o the other object to compare to this point
     * @return `true` if the other object is a `Point` object and its coordinates
     * are equal to this point's coordinates; `false` otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Returns a string representation of this point.
     *
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    public double getX() {
        return xyz.d1;
    }

    public double getY() {
        return xyz.d2;
    }

    public double getZ() {
        return xyz.d3;
    }
}
