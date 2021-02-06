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
    public UserPair findMatches(ArrayList<Integer> userIDs) {
        // TODO Fill in code for findMatches1
        return null;
    }

    public UserPair findMatches() {
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
    private ArrayList<UserPair> getPossibleCombinations(ArrayList<Integer> userIDs) {
        // TODO fill in code
        ArrayList<int> driverIDs = new ArrayList<>();
        ArrayList<int> passengerIDs = new ArrayList<>();
        for (int key : users.keySet()){
            if (users.get(key)[0][0] == 1.0){
                passengerIDs.add(key);
            }else{
                driverIDs.add(key);
            }
        }
        Double[] p_coords;
        return null;
    }

    // Finds the best path for each user (needs to be adapted to optimize for every user)
    private ArrayList<Double[]> bestPath(ArrayList<ArrayList<Road>> roads) {
        // TODO Fill in code for bestPath
        return null;
    }
}
