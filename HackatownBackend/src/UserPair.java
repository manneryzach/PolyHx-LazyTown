import java.util.ArrayList;

public class UserPair {
    public int passenger;
    public int driver;
    ArrayList<Road> route;

    public UserPair(int passenger, int driver, ArrayList<Road> route) {
        this.passenger = passenger;
        this.driver = driver;
        this.route = route;
    }

    public UserPair(int passenger, int driver) {
        this.passenger = passenger;
        this.driver = driver;
    }
}
