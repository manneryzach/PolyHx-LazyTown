import java.util.*;

public class RoadNetwork {
    /** Fields **/
    private HashMap<Double[], ArrayList<Road>> roads;
    private MapBuilder parser;

    /** Constructors **/
    public RoadNetwork(String JSONPath) {

    }

    /** Accessors **/
    public ArrayList<Road> getRoad(Double[] coordinates) {
        return roads.get(coordinates);
    }

    /** Mutators **/

    /** Custom methods **/
    public ArrayList<Road> shortestPath(Double[] coordA, Double[] coordB) {
        return null;
    }

}
