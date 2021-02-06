import DataTypes.Point;
import DataTypes.Road;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;

public class Drawing extends Canvas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map of montreal");
        Canvas canvas = new Drawing();
        canvas.setSize(800, 800);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    // Draws the map of montreal
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RoadNetwork map = new RoadNetwork("./data/routes.json");
        ArrayList<DataTypes.Point> coords = map.getSortedCoordinates();
        ArrayList<Road> uniqueRoads = new ArrayList<>();

        // Get unique roads
        for (DataTypes.Point p : coords) {
            for (Road r : map.getRoad(p))
                if(!uniqueRoads.contains(r)) uniqueRoads.add(r);
        }

        // Draw each road on the map. A road segment is represented by a line (p0, p1)
        for (Road r: uniqueRoads) {
            // Helps for visualization
            g2.setColor(new Color(r.getRoadSpeed()));

            //
            Iterator iter = r.getCoordinates().iterator();
            DataTypes.Point p0 = (DataTypes.Point) iter.next();
            Point p1 = (DataTypes.Point) iter.next();

            while(iter.hasNext()) {
                Point2D point0 = new Point2D.Double(p0.x, p0.y);
                Point2D point1 = new Point2D.Double(p1.x, p1.y);
                g2.draw(new Line2D.Double(point0, point1));

                // Update pointers
                DataTypes.Point temp = new DataTypes.Point(p1.x, p1.y);
                p1 = (DataTypes.Point) iter.next();
                p0 = temp;
            }
        }
    }
}
