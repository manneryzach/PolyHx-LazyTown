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
    private Point findClosestVertex(int userId) {
        return findClosestVertex(users.get(userId).coordA);
    }

    private double triangleMedian(Point A, Point B, Point C){
        // Apollonius Theorem
        double b = Point.eucDist(A, B);
        double c = Point.eucDist(A, C);
        double m = Point.eucDist(B, C)/2;
        return (Math.sqrt((Math.pow(i,2)+Math.pow(i,2))/2) - Math.pow(m,2))
    }

    private Point findClosestVertex(Point coord) {
        // Binary search
        int L = 0;
        int R = map.sortedCoordinates.size() - 1;
        int m;
        double c_norm = Point.eucNorm(coord);
        while (L<=R){
            m = (L+R)/2;
            if (Point.eucNorm(map.sortedCoordinate(m)) < c_norm){
                L = m + 1;
            }else if (Point.eucNorm(map.sortedCoordinates(m)) > c_norm){
                R = m - 1;
            }else{
                break;
            }
        }
        return sortedCoordinates(m);
    }

    // Pruning process
    private ArrayList<UserPair> getPossibleCombinations(ArrayList<Integer> userIDs) {
        // Separating drivers and passengers IDs
        ArrayList<Integer> driverIDs = new ArrayList<>();
        ArrayList<Integer> passengerIDs = new ArrayList<>();

        double cutoff = 0.5; // TODO find right cutoff value

        ArrayList<UserPair> pairs = new ArrayList<>();

        for (int key : userIDs) {
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
        point cA;
        point cB;
        int mA2 = 0;
        int mB2 = 0;
        for (Integer p_key : passengerIDs) {
            cA = findClosestVertex(users.get(p_key));
            cB = findClosestVertex(users.get(p_key));
            pA_norm = Point.eucNorm(cA);
            pB_norm = Point.eucNorm(cB);
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
                if (Point.eucNorm(d_path(mA)) > pA_norm){
                    mA2 = mA + 1;
                }else{
                    mA2 = mA - 1;
                }
                if (Point.eucNorm(d_path(mB)) > pA_norm){
                    mB2 = mB + 1;
                }else{
                    mB2 = mB - 1;
                }
                if (triangleMedian(cA, d_path(mA),d_path(mA2)) < cutoff && triangleMedian(cB, d_path(mB), d_path(mB2)) < cutoff) {
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
