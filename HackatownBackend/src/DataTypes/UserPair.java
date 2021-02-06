package DataTypes;

import java.util.ArrayList;

public class UserPair {
    public User passenger;
    public User driver;
    ArrayList<Point> route; // TODO Change to ArrayList<DataTypes.Point>

    public UserPair(User passenger, User driver, ArrayList<Point> route) {
        this.passenger = passenger;
        this.driver = driver;
        this.route = route;
    }

    public UserPair(User passenger, User driver) {
        this.passenger = passenger;
        this.driver = driver;
    }
}
