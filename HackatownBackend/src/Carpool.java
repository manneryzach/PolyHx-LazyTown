import java.util.*;

public class Carpool {
    // Fields
    private RoadNetwork map;
    private HashMap<Integer, Double[][]> users;

    // Constructors
    public Carpool(String JSONPAth, HashMap<Integer, Double[][]> users) {
        this.map = new RoadNetwork(JSONPAth);
        this.users = users;
    }

    public Carpool(String JSONPAth) {
        this.map = new RoadNetwork(JSONPAth);
    }

    // Accessors
    public HashMap<Integer, Double[][]> getUsers() {
        return this.users;
    }

    // Custom methods
    public UserPairs findMatches(ArrayList<Integer> userIDs) {
        return null;
    }

    public UserPairs findMatches() {
        return null;
    }

    // Helper methods
    private Road findClosestRoad(int userId) {
        return null;
    }

    private Double[][] findClosestVertex(Double[][] coord) {
        return null;
    }

    // Finds the best path for each user (needs to be adapted to optimize for every user)
    private ArrayList<Road> bestPath(ArrayList<ArrayList<Road>> roads) {
        return null;
    }
}
