import DataTypes.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

    public static void minHeapifyTest() {
        Point a = new Point();
        a.setDist(5.);

        Point b = new Point();
        b.setDist(11.);

        Point c = new Point();
        c.setDist(23.);

        Point d = new Point();
        d.setDist(18.);

        Point e = new Point();
        e.setDist(7.);

        Point f = new Point();
        f.setDist(46.);

        Point g = new Point();
        g.setDist(25.);

        Point h = new Point();
        h.setDist(4.);

        Point i = new Point();
        i.setDist(13.);


        Point[] p = {new Point(),a,b,c,d,e,f,g,h,i};
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(p));
        for (int j = 1; j < points.size() / 2; j++) {
            RoadNetwork.MinHeapify(points, j);
        }

        for (Point point : points) {
            System.out.println(point.getDist());
        }





    }

    public static void main(String[] args) {
        minHeapifyTest();
    }
}
