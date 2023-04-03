package primitives;

public class Vector extends Point{

   Vector(Double3 xyz) throws Exception {
        super(xyz);
        if (xyz==Double3.ZERO){
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }

    }
    public Vector(double dd1, double dd2, double dd3) throws Exception {
        this(new Double3(dd1, dd2, dd3));
        if (xyz==Double3.ZERO){
            throw new IllegalArgumentException("Vector cannot be zero vector");
        }


    }
    public Vector add(Vector v) throws Exception {
            return new Vector(this.xyz.add(v.xyz));
    }
    public Vector scale(double d) throws Exception {
        return new Vector(this.xyz.scale(d));
    }
    public double dotProdouct(Vector v)  {
        double dotProduct = v.xyz.d1 * this.xyz.d1 + v.xyz.d2 * this.xyz.d2 + v.xyz.d3 * this.xyz.d3;
        return dotProduct;
    }
    public Vector crossProduct(Vector v) throws Exception {
        double x =this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2;
        double y = this.xyz.d3 * v.xyz.d1 - this.xyz.d1 *v.xyz.d3;
        double z =this.xyz.d1 * v.xyz.d2 - this.xyz.d2* v.xyz.d1;
        return new Vector(this.xyz.add(v.xyz));
    }

    public double lengthSquared() {
        return dotProdouct(this);
    }
    public double length() {
        return Math.sqrt(lengthSquared());
    }
    public Vector normalize() throws Exception {
        double magnitude = Math.sqrt(this.xyz.d1*this.xyz.d1 + this.xyz.d2*this.xyz.d2 +this.xyz.d3*this.xyz.d3);
        return new Vector(xyz.d1 / magnitude, xyz.d2/ magnitude, xyz.d3 / magnitude);
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
