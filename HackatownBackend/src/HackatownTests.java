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
    }

    @Test
    void visualizeRoad() {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new HackatownTests();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }
}