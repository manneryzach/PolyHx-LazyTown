import java.util.*;

public class RoadNetwork {
    // Fields
    private HashMap<Double[], ArrayList<Road>> roads;
    private ArrayList<Double[]> sortedRoads;

    // Constructors
    public RoadNetwork(String JSONPath) throws Exception {
        this.roads = MapBuilder.parser(JSONPath);

        ArrayList<Double[]> roadArray = (ArrayList<Double[]>) roads.keySet();
        this.sortedRoads = Sorting.quickSort((ArrayList<Double[]>) roads.keySet(), 0, roadArray.size());
    }

    // Accessors
    public ArrayList<Road> getRoad(Double[] coordinates) {
        return roads.get(coordinates);
    }

    // Mutators

    // Custom methods
    public ArrayList<Road> shortestPath(Double[] coordA, Double[] coordB) {
        // TODO Fill in code for shortestPath
        return null;
    }

}
