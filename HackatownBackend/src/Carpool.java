import java.util.*;

public class Carpool {
    // Fields
    private RoadNetwork map;
    private HashMap<Integer, User> users;

    // Constructors
    public Carpool(String JSONPAth, HashMap<Integer, User> users) throws Exception {
        this.map = new RoadNetwork(JSONPAth);
        this.users = users;
    }

    public Carpool(String JSONPAth) throws Exception {
        this.map = new RoadNetwork(JSONPAth);
    }

    // Accessors
    public HashMap<Integer, User> getUsers() {
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
        // Separating drivers and passengers IDs
        ArrayList<Integer> driverIDs = new ArrayList<>();
        ArrayList<Integer> passengerIDs = new ArrayList<>();
        for (int key : users.keySet()){
            if (users.get(key).isPassenger){
                passengerIDs.add(key);
            }else{
                driverIDs.add(key);
            }
        }
        // Binary search to find least distance between driver path and passenger coords
        ArrayList<Point> d_path;
        int L;
        int R;
        int m;
        for (Integer p_key : passengerIDs){
            for (Integer d_key : driverIDs){
                d_path = map.shortestPath(users.get(d_key).coordA, users.get(d_key).coordB);
                L = 0;
                R = d_path.size() - 1;
                while (L <= R){
                    m = 1;
                }
            }
        }
        return null;
    }

    // Finds the best path for each user (needs to be adapted to optimize for every user)
    private ArrayList<Double[]> bestPath(ArrayList<ArrayList<Road>> roads) {
        // TODO Fill in code for bestPath
        return null;
    }
}
