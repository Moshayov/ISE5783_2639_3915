package primitives;

public class Point {
    final protected Double3 xyz;

    Point(Double3 xyz) {
        this.xyz = xyz;
    }
    public Point(double d1,double d2,double d3){
        this.xyz=new Double3(d1,d2,d3);

    }

    public Vector Subtract(Point p) throws Exception {
        return new Vector(this.xyz.subtract(p.xyz));
    }
    public  Point add(Vector v) throws Exception {
        return new Point(this.xyz.add(v.xyz));
    }
    public  double distanceSquared(Point point2) {
        double dx = xyz.d1 - point2.xyz.d1;
        double dy =xyz.d2 - point2.xyz.d2;
        double dz = xyz.d3 - point2.xyz.d3;
        return  Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
    public double distance(Point p2){
        double distanceSquared = distanceSquared(p2);
        return Math.sqrt(distanceSquared);
    }
    @Override
    public boolean equals(Object _object) {
        if (this == _object) return true;
        if (_object == null || getClass() != _object.getClass()) return false;

        Point _point = (Point) _object;
        return xyz.equals(_point.xyz);
    }
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

}
