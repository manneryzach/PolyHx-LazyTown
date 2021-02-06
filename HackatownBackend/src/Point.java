import java.util.Objects;

public class Point implements Comparable {
    Double x;
    Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Point(){
        this.x = 0.0;
        this.y = 0.0;
    }

    // returns <0 if this smaller than p, =0 if equal and >0 if bigger
    public int compareTo(Object p) {
        if (!(p instanceof Point))
            throw new IllegalArgumentException("Cannot compare points to other objects");

        // Double dist0 = eucNorm(this);
        // Double dist1 = eucNorm((Point) p);

        // Maybe type cast here is bad idea
        return (int) (eucNorm(this) - eucNorm((Point) p));
    }

    public static Double eucNorm(Point p) {
        return Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
    }

    public static Double eucDist(Point p0, Point p1) {
        return Math.sqrt(Math.pow((p0.x - p1.x), 2) + Math.pow((p0.y - p1.y), 2));
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
