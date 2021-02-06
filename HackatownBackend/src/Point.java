
public class Point {
    Double x;
    Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    // returns <0 if this smaller than p, =0 if equal and >0 if bigger
    public Double compareTo(Object p) {
        if (!(p instanceof Point))
            throw new IllegalArgumentException("Cannot compare points to other objects");

        // Double dist0 = eucNorm(this);
        // Double dist1 = eucNorm((Point) p);

        return eucNorm(this) - eucNorm((Point) p);
    }

    public static Double eucNorm(Point p) {
        return Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
    }

    public static Double eucDist(Point p0, Point p1) {
        return Math.sqrt(Math.pow((p0.x - p1.x), 2) + Math.pow((p0.y - p1.y), 2));
    }
}
