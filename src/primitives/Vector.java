package primitives;

public class Vector extends Point {
    /**
     * Constructs a Vector object with the specified xyz coordinates.
     *
     * @param xyz The Double3 object containing the x, y, and z coordinates of the vector
     * @throws IllegalArgumentException if the xyz coordinates are equal to zero
     */
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    /**
     * Constructs a Vector object with the specified x, y, and z coordinates.
     *
     * @param x The x-coordinate of the vector
     * @param y The y-coordinate of the vector
     * @param z The z-coordinate of the vector
     */
   // public Vector(double x, double y, double z) {
        //this(new Double3(x, y, z));
    //}
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO)){
            throw new IllegalArgumentException("Vector can't be vector(0,0,0)");
        }
    }

    /**
     * Adds the given vector to this vector and returns the result as a new Vector object.
     *
     * @param v The vector to add to this vector
     * @return A new Vector object representing the sum of this vector and the given vector
     */
    public Vector add(Vector v) {
        return new Vector(this.xyz.add(v.xyz));
    }

    /**
     * Scales this vector by the given factor and returns the result as a new Vector object.
     *
     * @param number The factor by which to scale this vector
     * @return A new Vector object representing this vector scaled by the given factor
     */
    //public Vector scale(double d) {
        //return new Vector(xyz.scale(d));
    //}
    public Vector scale(double number) {
        double x = this.xyz.d1 * number;
        double y = this.xyz.d2 * number;
        double z = this.xyz.d3 * number;

        return new Vector(x, y, z);

    }

    /**
     * Calculates the dot product of this vector with the given vector.
     *
     * @param v The vector to calculate the dot product with
     * @return The dot product of this vector and the given vector
     */
    public double dotProdouct(Vector v) {
        return (v.xyz.d1 * this.xyz.d1
                + v.xyz.d2 * this.xyz.d2
                + v.xyz.d3 * this.xyz.d3);
    }

    /**
     * Calculates the cross product of this vector with the given vector and returns the result as a new Vector object.
     *
     * @param v The vector to calculate the cross product with
     * @return A new Vector object representing the cross product of this vector and the given vector
     */
    public Vector crossProduct(Vector v) {
        double x = this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2;
        double y = this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3;
        double z = this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1;

        return new Vector(x, y, z);
    }

    /**
     * Calculates the square of the length of this vector.
     *
     * @return The square of the length of this vector
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1
                + xyz.d2 * xyz.d2
                + xyz.d3 * xyz.d3;
    }

    /**
     * Calculates the length of this vector.
     *
     * @return The length of this vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Returns a new Vector object that is a unit vector in the same direction as this vector.
     *
     * @return A new Vector object representing a unit vector in the same direction as this vector
     */
    public Vector normalize() {
        double len = length();
        double x = this.xyz.d1/len;
        double y = this.xyz.d2/len;
        double z = this.xyz.d3/len;
        return new Vector(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';

    }

    /**
     * Determines whether this vector is equal to another object.
     *
     * @param o the other object to compare to this vector
     * @return `true` if the other object is a `Vector` object and its coordinates
     * are equal to this vector's coordinates; `false` otherwise
     */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }
}
