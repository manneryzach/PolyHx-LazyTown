import DataTypes.Point;
import DataTypes.Road;

import java.util.*;

public class RoadNetwork {
    // Fields
    private HashMap<Point, ArrayList<Road>> roads;
    private ArrayList<Point> sortedCoordinates;

    // Constructors
    public RoadNetwork(String JSONPath) {
        // Initialise hashmap
        try {
            this.roads = MapBuilder.parser(JSONPath);
            // Initialize sorted roads
            ArrayList<Point> pointArray = new ArrayList<>(roads.keySet());
            Sorting.quickSort(pointArray, 0, pointArray.size() - 1);

            this.sortedCoordinates = pointArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Accessors
    public ArrayList<Road> getRoad(Point coordinates) {
        return roads.get(coordinates);
    }

    public ArrayList<Point> getSortedCoordinates() {
        return sortedCoordinates;
    }

    /**
     * Computes the shortest weighted path from one road intersection to another using Dijkstra's algorithm.
     * The weight of each road is calculated with respect its the speed.
     * @param coordA Starting point
     * @param coordB End point
     * @return A sequence of intersection representing the optimal route.
     * **/
    public ArrayList<Point> shortestPath(Point coordA, Point coordB) {
        if(!roads.containsKey(coordA) || !roads.containsKey(coordB))
            throw new IllegalArgumentException("Coordinates must be intersections on the map");

        ArrayList<Point> vertices = new ArrayList<>(roads.keySet());

        for (Point v : roads.keySet()) {
            v.setDist(Double.MAX_VALUE);
            v.setPrev(null);
        }
        coordA.setDist(0.0);

        while (!vertices.isEmpty()) {
            Point u = findMin(vertices);

            vertices.remove(u);
            if (u.equals(coordA)) break;

            for (Road outRoad : roads.get(u)) {
                Double alt = u.getDist() + outRoad.getWeight();
                if (alt < outRoad.getNextPoint().getDist()) {
                    outRoad.getNextPoint().setDist(alt);
                    outRoad.getNextPoint().setPrev(u);
                }
            }
        }

        LinkedList<Point> route = new LinkedList<>();
        Point u = coordB;
        while (u.getPrev() != null && u != coordA) {
            route.addFirst(u);
            u = u.getPrev();
        }

        return new ArrayList<>(route);
    }

    private Point findMin(ArrayList<Point> vertices) {
        return null;
    }

}
