package DataTypes;

import java.util.Objects;

public class Point implements Comparable {
    public Double x;
    public Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Point(){
        this.x = 0.0;
        this.y = 0.0;
    }

    public static Double eucNorm(Point p) {
        return Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
    }

    public static Double eucDist(Point p0, Point p1) {
        return Math.sqrt(Math.pow((p0.x - p1.x), 2) + Math.pow((p0.y - p1.y), 2));
    }

    public static Point transform(Point p){
        double x = p.y;
        double y = p.x;
        double scale_factor = 5;
        x = x - (-73.55011);
        y = y - (45.5123);
        x = x*(108.6)*scale_factor;
        y = (108.6*scale_factor*45.5123) - y*(108.6)*scale_factor;
        return new Point(x, y);
    }

    // returns <0 if this smaller than p, =0 if equal and >0 if bigger
    public int compareTo(Object o) {
        if (!(o instanceof Point))
            throw new IllegalArgumentException("Cannot compare points to other objects");

        if (this.equals(o))
            return 0;

        Double dist0 = eucNorm(this);
        Double dist1 = eucNorm((DataTypes.Point) o);

        if (dist0 - dist1 == 0) {
            if (this.x == ((Point) o).x)
                if (this.y < ((Point) o).y)
                    return -1;
            if (this.y > ((Point) o).y)
                return 1;
            if (this.x < ((Point) o).x) return -1;
            return 1;
        }

        if (dist0 - dist1 < 0) return -1;
        else return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
