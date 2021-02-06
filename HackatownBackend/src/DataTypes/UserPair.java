package DataTypes;

import java.util.ArrayList;

public class UserPair {
    public User passenger;
    public User driver;
    public ArrayList<Point> route;
    public double time_score;

    public UserPair(User passenger, User driver, ArrayList<Point> route, double time_score) {
        this.passenger = passenger;
        this.driver = driver;
        this.route = route;
        this.time_score = time_score;
    }

    public UserPair(User passenger, User driver) {
        this.passenger = passenger;
        this.driver = driver;
    }
}
