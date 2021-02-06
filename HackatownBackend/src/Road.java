import java.util.ArrayList;

public class Road {
    // FIELDS
    private String name;
    private ArrayList<Double[]> coordinates;
    private long direction;
    private int roadSpeed;

    // Constructor
    public Road(String name, ArrayList<Double[]> coordinates, long direction, int roadSpeed) {
        this.name = name;
        this.coordinates = coordinates;
        this.direction = direction;
        this.roadSpeed = roadSpeed;
    }

    // Accessors
    public String getName() {
        return name;
    }

    public ArrayList<Double[]> getCoordinates() {
        return coordinates;
    }

    public long getDirection() {
        return direction;
    }

    public int getRoadSpeed() {
        return roadSpeed;
    }
}
