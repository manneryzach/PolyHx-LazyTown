import DataTypes.Point;
import DataTypes.Road;
import DataTypes.User;
import DataTypes.UserPair;

import java.lang.reflect.Array;
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

    public Carpool() {

    }

    // Accessors
    public HashMap<Integer, User> getUsers() {
        return this.users;
    }

    // Custom methods
    public UserPair findMatches() {
        return findMatches(new ArrayList<Integer>(users.keySet()));
    }

    public UserPair findMatches(ArrayList<Integer> userIDs) {
        ArrayList<UserPair> pairs = getPossibleCombinations(userIDs);

        ArrayList<UserPair> base = new ArrayList<>();


        ArrayList<Integer> driverIDs = new ArrayList<>();
        ArrayList<Integer> passengerIDs = new ArrayList<>();

        HashMap<Integer, ArrayList<Integer>> driver_passengers = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> passenger_drivers = new HashMap<>();

        for (UserPair pair : pairs){
            if (!driverIDs.contains(pair.driverID))
                driverIDs.add(pair.driverID);
            if (!driver_passengers.containsKey(pair.driverID))
                driver_passengers.put(pair.driverID, new ArrayList<>(Arrays.asList(pair.passengerID)));
            else driver_passengers.get(pair.driverID).add(pair.passengerID);

            if (!passengerIDs.contains(pair.passengerID)) passengerIDs.add(pair.passengerID);
            if (!passenger_drivers.containsKey(pair.passengerID)) {
                passenger_drivers.put(pair.passengerID, new ArrayList<>());
            }
            passenger_drivers.get(pair.passengerID).add(pair.driverID);

        }

        for (int passenger : passenger_drivers.keySet()){
            ArrayList<Integer> drivers = passenger_drivers.get(passenger);
            // Pop last
            Integer driver = drivers.get(drivers.size() - 1);
            drivers.remove(drivers.size() - 1);
            for (int passenger2 : driver_passengers.get(driver)) {
                passenger_drivers.get(passenger2).remove(driver);
            }

        }
        return null;
    }

    public ArrayList<ArrayList<UserPair>> findMatches(HashMap<Integer, ArrayList<Integer>> driver_passengers,
                                                      HashMap<Integer, ArrayList<Integer>> passenger_drivers,
                                                      ArrayList<UserPair> curCombo){
        ArrayList<ArrayList<UserPair>> pairCombos = new ArrayList<>();
        // Base Case
        if (passenger_drivers.keySet().size() != 1){
            for (int passenger : passenger_drivers.keySet()){
                ArrayList<Integer> drivers = passenger_drivers.get(passenger);
                // Pop last
                Integer driver = drivers.get(drivers.size() - 1);
                drivers.remove(drivers.size() - 1);

                ArrayList<UserPair> nextCombo = (ArrayList<UserPair>) curCombo.clone();
                nextCombo.add(new UserPair(users.get(passenger), users.get(driver), passenger, driver));

                for (int passenger2 : driver_passengers.get(driver))
                    passenger_drivers.get(passenger2).remove(driver);

                pairCombos.addAll(findMatches(driver_passengers, passenger_drivers, nextCombo));
            }
        }else{
            for (int passenger : passenger_drivers.keySet()){
                for (int driver : passenger_drivers.get(passenger)) {
                    ArrayList<UserPair> nextCombo = (ArrayList<UserPair>) curCombo.clone();
                    nextCombo.add(new UserPair(users.get(passenger), users.get(driver), passenger, driver));
                    pairCombos.add(nextCombo);
                }
            }
        }
        return pairCombos;
    }


    //    function findPairs
    //
    //    for drivers in P1:
    //          choose driver
    //          delete P1 in all other drivers
    //          return findPairs(next driver)
    //

    // Helper methods
    public Point findClosestVertex(int userId) {
        return findClosestVertex(users.get(userId).coordA);
    }

    private double triangleMedian(Point A, Point B, Point C) {
        // Apollonius Theorem
        double b = Point.eucDist(A, B);
        double c = Point.eucDist(A, C);
        double m = Point.eucDist(B, C) / 2;
        return (Math.sqrt((Math.pow(b, 2) + Math.pow(c, 2)) / 2) - Math.pow(m, 2));
    }

    public Point findClosestVertex(Point coord) {
        // Binary search
        int L = 0;
        int R = map.getSortedCoordinates().size() - 1;
        int m = (L + R) / 2;
        double c_norm = Point.eucNorm(coord);
        while (L <= R) {
            m = (L + R) / 2;
            if (Point.eucNorm(map.getSortedCoordinates().get(m)) < c_norm) {
                L = m + 1;
            } else if (Point.eucNorm(map.getSortedCoordinates().get(m)) > c_norm) {
                R = m - 1;
            } else {
                break;
            }
        }
        return map.getSortedCoordinates().get(m);
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
        int mA2 = 0;
        int mB2 = 0;
        for (Integer p_key : passengerIDs) {
            Point cA = findClosestVertex(users.get(p_key).coordA);
            Point cB = findClosestVertex(users.get(p_key).coordB);
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
                    if (Point.eucNorm(d_path.get(mB)) > pB_norm)
                        RB = mA - 1;
                    else if (Point.eucNorm(d_path.get(mB)) < pB_norm)
                        LB = mB + 1;
                    else
                        break;

                }

                if (Point.eucNorm(d_path.get(mA)) > pA_norm)
                    mA2 = mA + 1;
                else
                    mA2 = mA - 1;

                if (Point.eucNorm(d_path.get(mB)) > pA_norm)
                    mB2 = mB + 1;
                else
                    mB2 = mB - 1;

                if (triangleMedian(cA, d_path.get(mA), d_path.get(mA2)) < cutoff &&
                        triangleMedian(cB, d_path.get(mB), d_path.get(mB2)) < cutoff)
                    pairs.add(new UserPair(users.get(p_key), users.get(d_key), p_key, d_key));
            }
        }
        return pairs;
    }

    // Finds the best path for each user (needs to be adapted to optimize for every user)
    private UserPair carpoolPath(UserPair pair) {
        User driver = pair.driver;
        User passenger = pair.passenger;
        Point pA = findClosestVertex(passenger.coordA);
        Point pB = findClosestVertex(passenger.coordB);

        ArrayList<Point> route = map.shortestPath(driver.coordA, pA);
        route.addAll(map.shortestPath(driver.coordA, pB));
        route.addAll(map.shortestPath(pB, driver.coordB));

        double time_score = 0;
        double speed = 50;

        for (int i=0; i<(route.size()-2);i++){
            ArrayList<Road> possible_roads = map.getRoads(route.get(i));
            for (Road possible_road : possible_roads){
                if (possible_road.getNextPoint().compareTo(route.get(i+1)) == 0){
                    speed = possible_road.getRoadSpeed();
                    break;
                }
            }
            time_score += Point.eucDist(route.get(i),route.get(i+1))/speed;
        }
        return new UserPair(passenger, driver, pair.passengerID, pair.driverID, route, time_score);
    }
}
