import java.util.*;

public class RoadNetwork {
    // Fields
    private HashMap<Point, ArrayList<Road>> roads;
    private ArrayList<Point> sortedRoads;

    // Constructors
    public RoadNetwork(String JSONPath) throws Exception {
        this.roads = MapBuilder.parser(JSONPath);

        ArrayList<Point> roadArray = (ArrayList<Point>) roads.keySet();
        Sorting.quickSort(roadArray, 0, roadArray.size() - 1);

        this.sortedRoads = roadArray;
    }

    // Accessors
    public ArrayList<Road> getRoad(Double[] coordinates) {
        return roads.get(coordinates);
    }

    // Mutators

    // Custom methods
    public ArrayList<Point> shortestPath(Point coordA, Point coordB) {
        // TODO Fill in code for shortestPath
        return null;
    }

}
