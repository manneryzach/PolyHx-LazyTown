import java.util.*;

public class User{
    boolean isPassenger;
    Double[] coords;
    Double[] coordA;
    Double[] coordB;

    public User(Double[] coords){
        this.isPassenger = true;
        this.coords = coords;
        this.coordA = [0];
        this.coordB = [0];
    }

    public User(Double[] coordA, Double[] coordB){
        this.isPassenger = false;
        this.coords = [0];
        this.coordA = coordA;
        this.coordB = coordB;
    }
}
