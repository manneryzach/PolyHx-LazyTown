import DataTypes.Point;
import DataTypes.Road;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Drawing extends Canvas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map of montreal");
        Canvas canvas = new Drawing();
        canvas.setSize(1920, 1080);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    // Draws the map of montreal
    public void paint(Graphics g) {
        System.out.println("Drawing");

        Graphics2D g2 = (Graphics2D) g;
        RoadNetwork map = new RoadNetwork("./data/routes.json");
        ArrayList<DataTypes.Point> coords = map.getSortedCoordinates();
        ArrayList<Road> uniqueRoads = new ArrayList<>();

        // Get unique roads
        for (DataTypes.Point p : coords) {
            for (Road r : map.getRoad(p))
                if (!uniqueRoads.contains(r)) uniqueRoads.add(r);
        }
        // Draw each road on the map. A road segment is represented by a line (p0, p1)
        for (Point p0 : coords) {
            for (Road r : map.getRoad(p0)) {
                Point p1 = r.getNextPoint();
                // Linear transformation
                Point p0_trans = Point.transform(p0);
                Point p1_trans = Point.transform(p1);

                System.out.println("Plotting line from " + p0_trans + " to " + p1_trans + " with speed " + r.getRoadSpeed());
                Point2D point0 = new Point2D.Double(p0_trans.x, p0_trans.y);
                Point2D point1 = new Point2D.Double(p1_trans.x, p1_trans.y);
                g2.draw(new Line2D.Double(point0, point1));
            }
        }
        System.out.println("Done");
    }
}
