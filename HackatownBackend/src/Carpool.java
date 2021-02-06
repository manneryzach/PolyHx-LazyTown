import java.util.*;

public class Carpool {
    /**
     * Fields
     **/
    private RoadNetwork map;
    private HashMap<Integer, Double[][]> users;

    /**
     * Constructors
     **/
    public Carpool(String JSONPAth, HashMap<Integer, Double[][]> users) {
        this.map = new RoadNetwork(JSONPAth);
        this.users = users;
    }

    public Carpool(String JSONPAth) {
        this.map = new RoadNetwork(JSONPAth);
    }

    /**
     * Accessors
     **/
    public HashMap<Integer, Double[][]> getUsers() {
        return this.users;
    }

    /** Mutators **/

    /**
     * Custom methods
     **/
    public UserPairs findMatches(ArrayList<Integer> userIDs) {
        return null;
    }

    public UserPairs findMatches() {
        return null;
    }

}
