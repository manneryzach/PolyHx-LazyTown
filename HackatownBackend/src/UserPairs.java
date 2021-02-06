import java.util.ArrayList;

public class UserPairs {
    public int passenger;
    public int driver;
    ArrayList<Road> route;

    public UserPairs(int passenger, int driver, ArrayList<Road> route) {
        this.passenger = passenger;
        this.driver = driver;
        this.route = route;
    }
}
