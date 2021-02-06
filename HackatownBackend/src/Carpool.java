import DataTypes.Point;
import DataTypes.Road;
import DataTypes.User;
import DataTypes.UserPair;

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

        return findClosestVertex(users.get(userId).coordA);
    }

    private Double[][] findClosestVertex(Point coord) {
        // TODO Fill in code for findClosestVertex for coords
        return null;
    }

    // Pruning process
    private ArrayList<UserPair> getPossibleCombinations(ArrayList<Integer> userIDs) {
        // Fixme Changed user pair to contain users and not userIDs (line 108 broken)
        // TODO fill in code
        // Separating drivers and passengers IDs
        ArrayList<Integer> driverIDs = new ArrayList<>();
        ArrayList<Integer> passengerIDs = new ArrayList<>();

        double cutoff = 0.5; // TODO find right cutoff value

        ArrayList<UserPair> pairs = new ArrayList<>();

        for (int key : users.keySet()) {
            if (((users.get(key)).isPassenger) == true) {
                passengerIDs.add(key);
            } else {
                driverIDs.add(key);
            }
        }
        // Binary search to find least distance between driver path and passenger coords
        ArrayList<Point> d_path;
        int LA = 0;
        int LB = 0;
        int RA = 0;
        int RB = 0;
        int mA = 0;
        int mB = 0;
        double pA_norm;
        double pB_norm;
        for (Integer p_key : passengerIDs) {
            pA_norm = Point.eucNorm(users.get(p_key).coordA);
            pB_norm = Point.eucNorm(users.get(p_key).coordB);
            for (Integer d_key : driverIDs) {
                d_path = map.shortestPath(users.get(d_key).coordA, users.get(d_key).coordB);
                Sorting.quickSort(d_path, 0, d_path.size() - 1);
                LA = 0;
                LB = 0;
                RA = d_path.size() - 1;
                RB = RA;
                // Binary search coord A
                while (LA <= RA) {
                    mA = (LA + RA) / 2;
                    if (Point.eucNorm(d_path.get(mA)) > pA_norm) {
                        RA = mA - 1;
                    } else if (Point.eucNorm(d_path.get(mA)) < pA_norm) {
                        LA = mA + 1;
                    } else {
                        break;
                    }
                }
                // Binary search coord B
                while (LB <= RB) {
                    mB = (LB + RB) / 2;
                    if (Point.eucNorm(d_path.get(mB)) > pB_norm) {
                        RB = mA - 1;
                    } else if (Point.eucNorm(d_path.get(mB)) < pB_norm) {
                        LB = mB + 1;
                    } else {
                        break;
                    }
                }
                // FIXME Make it an interval
                if (Point.eucDist(users.get(p_key).coordA, d_path.get(mA)) < cutoff && Point.eucDist(users.get(p_key).coordB, d_path.get(mB)) < cutoff) {
                    pairs.add(new UserPair(users.get(p_key), users.get(d_key)));
                }
            }
        }
        return pairs;
    }

    // Finds the best path for each user (needs to be adapted to optimize for every user)
    private ArrayList<Double[]> bestPath(ArrayList<ArrayList<Road>> roads) {
        // TODO Fill in code for bestPath
        return null;
    }
}
