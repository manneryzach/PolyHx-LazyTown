package DataTypes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Road {
    // FIELDS
    private String name;
    private LinkedList<Point> coordinates;
    private Point p0;
    private Point p1;
    private long direction;
    private int roadSpeed;

    // Constructor
    public Road(String name, LinkedList<Point> coordinates, long direction, int roadSpeed) {
        this.name = name;
        this.coordinates = coordinates;
        this.direction = direction;
        this.roadSpeed = roadSpeed;
    }

    public Road(String name, long direction, int roadSpeed) {
        this.name = name;
        this.coordinates = new LinkedList<>();
        this.direction = direction;
        this.roadSpeed = roadSpeed;
    }

    public Road(String name, int roadSpeed) {
        this.name = name;
        this.coordinates = new LinkedList<>();
        this.direction = direction;
        this.roadSpeed = roadSpeed;
    }

    // Accessors
    public String getName() {
        return name;
    }

    public LinkedList<Point> getCoordinates() {
        return coordinates;
    }

    public long getDirection() {
        return direction;
    }

    public int getRoadSpeed() {
        return roadSpeed;
    }

    public Point getNextRoad() {
        return this.p1;
    }

    public Double getWeight() {
        return Point.eucDist(p0, p1) / roadSpeed;
    }


}
