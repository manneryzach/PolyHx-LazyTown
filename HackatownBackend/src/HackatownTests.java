import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class HackatownTests {

    @Test
    void pointSorting() {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(3.0, 4.0));
        points.add(new Point(6.0, 8.0));
        Sorting.quickSort(points, 0, 1);

        System.out.println(points);
    }

}