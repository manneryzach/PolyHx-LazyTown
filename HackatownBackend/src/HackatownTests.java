import DataTypes.Point;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.awt.Canvas;
import javax.swing.JFrame;


class HackatownTests extends Canvas{

    @Test
    void pointSorting() {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(3.0, 4.0));
        points.add(new Point(6.0, 8.0));
        Sorting.quickSort(points, 0, 1);

        System.out.println(points);

        RoadNetwork map = new RoadNetwork("./data/routes.json");
        ArrayList<DataTypes.Point> coords = map.getSortedCoordinates();
        ArrayList<Double> norms = new ArrayList<>();
        for (Point p : coords) {
            norms.add(Point.eucNorm(p));
        }
        System.out.println(norms);
        for (int i = 1; i < norms.size(); i++) {
            if (norms.get(i - 1) > norms.get(i))
                throw new RuntimeException("Array is not properly sorted at point" + coords.get(i));
        }
    }
}