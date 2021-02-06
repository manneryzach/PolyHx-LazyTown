import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;

public class Drawing extends Canvas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map of montreal");
        Canvas canvas = new Drawing();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RoadNetwork map = new RoadNetwork("./routes.json");

        for (Point p: map.getSortedCoordinates()) {
            Ellipse2D point = new Ellipse2D.Double(p.x, p.y, 5.0, 5.0);
            g2.draw(point);
        }

    }
}
