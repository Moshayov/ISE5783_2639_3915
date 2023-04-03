package primitives;

import java.util.Objects;

public class Ray {

    final private Point P0;
    final private Vector dir;

    public Point getP0() {
        return P0;
    }

    public Ray(Point p0, Vector d) throws Exception {
        P0 = p0;
        dir = d.normalize();
    }
    public Vector getDir() {
        return dir;
    }
    @Override
    public String toString() {
        return "Ray{" +
                "P0=" + P0 +
                ", dir=" + dir +
                '}';
    }
    @Override
    public boolean equals(Object _object) {
        if (this == _object) return true;
        if (!(_object instanceof Ray ray)) return false;
        return P0.equals(ray.P0) && dir.equals(ray.dir);
    }

}
