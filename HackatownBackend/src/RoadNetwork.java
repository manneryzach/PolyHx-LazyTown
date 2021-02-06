import java.util.*;

public class RoadNetwork {
    // Fields
    private HashMap<Point, ArrayList<Road>> roads;
    private ArrayList<Point> sortedCoordinates;

    // Constructors
    public RoadNetwork(String JSONPath) {
        // Initialise hashmap
        try {
            this.roads = MapBuilder.parser(JSONPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Initialize sorted roads
        ArrayList<Point> pointArray = (ArrayList<Point>) roads.keySet();
        Sorting.quickSort(pointArray, 0, pointArray.size() - 1);

        this.sortedCoordinates = pointArray;
    }

    // Accessors
    public ArrayList<Road> getRoad(Point coordinates) {
        return roads.get(coordinates);
    }

    public ArrayList<Point> getSortedCoordinates() {
        return sortedCoordinates;
    }

    // Mutators

    // Custom methods
    public ArrayList<Point> shortestPath(Point coordA, Point coordB) {
        // TODO Fill in code for shortestPath
        return null;
    }

}
