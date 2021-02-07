package DataTypes;

import java.util.ArrayList;

public class UserPair {
    public User passenger;
    public int passengerID;
    public int driverID;
    public User driver;
    public ArrayList<Point> route;
    public double time_score;

    public UserPair(User passenger, User driver, int passengerID, int driverID, ArrayList<Point> route, double time_score) {
        this.passenger = passenger;
        this.driver = driver;
        this.passengerID = passengerID;
        this.driverID = driverID;
        this.route = route;
        this.time_score = time_score;
    }

    public UserPair(User passenger, User driver, int passengerID, int driverID) {
        this.passenger = passenger;
        this.driver = driver;
        this.passengerID = passengerID;
        this.driverID = driverID;
    }
}
