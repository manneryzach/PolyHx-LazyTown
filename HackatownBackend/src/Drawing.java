import DataTypes.Point;
import DataTypes.Road;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;

public class Drawing extends Canvas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map of montreal");
        Canvas canvas = new Drawing();
        canvas.setSize(1920, 1080);
        frame.add(canvas);
        frame.pack();
        //frame.setVisible(true);
    }

    // Draws the map of montreal
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        Carpool carCool = null;
        try {
            carCool = new Carpool("./data/routes.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RoadNetwork map = carCool.getMap();
        ArrayList<DataTypes.Point> coords = map.getSortedCoordinates();

        // Draw each road on the map. A road segment is represented by a line (p0, p1)
        for (Point p0 : coords) {
            for (Road r : map.getRoads(p0)) {
                Point p1 = r.getNextPoint();
                // Linear transformation
                Point p0_trans = Point.transform(p0);
                Point p1_trans = Point.transform(p1);

                // System.out.println("Plotting line from " + p0 + " to " + p1 + " with speed " + r.getRoadSpeed());
                Point2D point0 = new Point2D.Double(p0_trans.x, p0_trans.y);
                Point2D point1 = new Point2D.Double(p1_trans.x, p1_trans.y);
                g2.draw(new Line2D.Double(point0, point1));
            }
        }

        Point A = null;
        Point B = null;
        try {
            A = carCool.findClosestVertex(new Point(-73.5552140552382, 45.5268750230444));
            B = carCool.findClosestVertex(new Point(-73.5764252813395, 45.492250906316));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Finding route from " + A + " to " + B);

        ArrayList<Point> route = map.shortestPath(A, B);
        Iterator iter = route.iterator();
        Point p0 = (Point) iter.next();
        Point p1 = (Point) iter.next();

        do {
            g2.setColor(new Color(0, 0, 255));
            g2.setStroke(new BasicStroke(10));

            Point p0_trans = Point.transform(p0);
            Point p1_trans = Point.transform(p1);
            System.out.println("Plotting at: (" + p0_trans.x + ", " + p0_trans.y + ")");
            Point2D point0 = new Point2D.Double(p0_trans.x, p0_trans.y);
            Point2D point1 = new Point2D.Double(p1_trans.x, p1_trans.y);
            g2.draw(new Rectangle2D.Double(p1_trans.x, p1_trans.y, 50, 50));
            g2.draw(new Rectangle2D.Double(p0_trans.x, p0_trans.y, 50, 50));
            g2.draw(new Line2D.Double(point0, point1));

            Point temp = new Point(p1.x, p1.y);
            p1 = (Point) iter.next();
            p0 = temp;
        } while (iter.hasNext());

        System.out.println("Done");
    }
}
