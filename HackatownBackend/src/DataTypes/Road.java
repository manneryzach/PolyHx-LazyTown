package DataTypes;

import java.util.ArrayList;

public class Road {
    // FIELDS
    private String name;
    private ArrayList<Point> coordinates;
    private long direction;
    private int roadSpeed;

    // Constructor
    public Road(String name, ArrayList<Point> coordinates, long direction, int roadSpeed) {
        this.name = name;
        this.coordinates = coordinates;
        this.direction = direction;
        this.roadSpeed = roadSpeed;
    }

    public Road(String name, long direction, int roadSpeed) {
        this.name = name;
        this.coordinates = new ArrayList<>();
        this.direction = direction;
        this.roadSpeed = roadSpeed;
    }

    // Accessors
    public String getName() {
        return name;
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }

    public long getDirection() {
        return direction;
    }

    public int getRoadSpeed() {
        return roadSpeed;
    }
}
