import java.util.*;

public class Carpool {
    // Fields
    private RoadNetwork map;
    private HashMap<Integer, Double[][]> users;

    // Constructors
    public Carpool(String JSONPAth, HashMap<Integer, Double[][]> users) throws Exception {
        this.map = new RoadNetwork(JSONPAth);
        this.users = users;
    }

    public Carpool(String JSONPAth) throws Exception {
        this.map = new RoadNetwork(JSONPAth);
    }

    // Accessors
    public HashMap<Integer, Double[][]> getUsers() {
        return this.users;
    }

    // Custom methods
    public UserPairs findMatches(ArrayList<Integer> userIDs) {
        // TODO Fill in code for findMatches1
        return null;
    }

    public UserPairs findMatches() {
        // TODO Fill in code for findMatches1
        return null;
    }

    // Helper methods
    private Road findClosestRoad(int userId) {
        // TODO Fill in code for findClosestRoad
        return null;
    }

    private Double[][] findClosestVertex(Double[][] coord) {
        // TODO Fill in code for findClosestVertex
        return null;
    }

    // Finds the best path for each user (needs to be adapted to optimize for every user)
    private ArrayList<Road> bestPath(ArrayList<ArrayList<Road>> roads) {
        // TODO Fill in code for bestPath
        return null;
    }
}
