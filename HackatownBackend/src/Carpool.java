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
    private Road findClosestVertex(int userId) {
        // TODO Fill in code for findClosestVertex for userID
        return null;
    }

    private Double[][] findClosestVertex(Double[][] coord) {
        // TODO Fill in code for findClosestVertex for coords
        return null;
    }

    // Pruning process
    private UserPairs getPossibleCombinations(ArrayList<Integer> userIDs) {
        // TODO fill in code
        return null;
    }

    // Finds the best path for each user (needs to be adapted to optimize for every user)
    private ArrayList<Double[]> bestPath(ArrayList<ArrayList<Road>> roads) {
        // TODO Fill in code for bestPath
        return null;
    }
}
