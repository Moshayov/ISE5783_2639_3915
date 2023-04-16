package primitives;

public class Vector extends Point {

    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }
    }

    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    public Vector add(Vector v) {
        return new Vector(this.xyz.add(v.xyz));
    }

    public Vector scale(double d) {
        return new Vector(this.xyz.scale(d));
    }

    public double dotProdouct(Vector v) {
        double dotProduct = v.xyz.d1 * this.xyz.d1 + v.xyz.d2 * this.xyz.d2 + v.xyz.d3 * this.xyz.d3;
        return dotProduct;
    }

    public Vector crossProduct(Vector v) {
        double x = this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2;
        double y = this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3;
        double z = this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1;

        return new Vector(x,y,z);
    }

    public double lengthSquared() {
        return dotProdouct(this);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double magnitude = Math.sqrt(this.xyz.d1 * this.xyz.d1 + this.xyz.d2 * this.xyz.d2 + this.xyz.d3 * this.xyz.d3);
        return new Vector(xyz.d1 / magnitude, xyz.d2 / magnitude, xyz.d3 / magnitude);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object _object) {
        if (this == _object) return true;
        if (_object == null || getClass() != _object.getClass()) return false;

        return super.equals(_object);
    }
}
