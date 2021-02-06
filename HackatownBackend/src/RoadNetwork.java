import java.util.*;

public class RoadNetwork {
    // Fields
    private HashMap<Point, ArrayList<Road>> roads;
    private ArrayList<Point> sortedRoads;

    // Constructors
    public RoadNetwork(String JSONPath) throws Exception {
        this.roads = MapBuilder.parser(JSONPath);

        ArrayList<Point> roadArray = (ArrayList<Point>) roads.keySet();
        Sorting.quickSort((ArrayList<Point>) roads.keySet(), 0, roadArray.size());

        this.sortedRoads = roadArray;
    }

    // Accessors
    public ArrayList<Road> getRoad(Double[] coordinates) {
        return roads.get(coordinates);
    }

    // Mutators

    // Custom methods
    public ArrayList<Double[]> shortestPath(Double[] coordA, Double[] coordB) {
        // TODO Fill in code for shortestPath
        return null;
    }

}
