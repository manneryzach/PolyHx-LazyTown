import java.util.*;

public class User{
    boolean isPassenger;
    Point coords;
    Point coordA;
    Point coordB;

    public User(Point coords, Point coordA, Point coordB){
        this.isPassenger = true;
        this.coords = coords;
        this.coordA = coordA;
        this.coordB = coordB;
    }

    public User(Point coordA, Point coordB){
        this.isPassenger = false;
        this.coords = new Point();
        this.coordA = coordA;
        this.coordB = coordB;
    }
}
