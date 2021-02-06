package DataTypes;

public class User{
    public boolean isPassenger;
    public Point coordA;
    public Point coordB;

    public User(boolean isPassenger, Point coordA, Point coordB){
        this.isPassenger = isPassenger;
        this.coordA = coordA;
        this.coordB = coordB;
    }
}
