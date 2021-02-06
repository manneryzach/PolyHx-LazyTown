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

        ArrayList<Point> vertices = new ArrayList<>();

        for (Point v : roads.keySet()) {
            v.setDist(-1.0);
            v.setPrev(null);
        }
        coordA.setDist(0.0);

        while (!vertices.isEmpty()) {
            Point u =
        }
            //Somehow import the graph/the roads
                //Start from PointA

                //LOOP UNTIL ALL INTERSECTIONS ARE VISITED
                // {
                      //Check all roads connected to the current point
                      //Check the weighted distance to the Next Intersection
                         //Change value of that Intersection to the smallest weight available (through addition)
                         //Change node to visited once all paths are checked and the smallest weight is found
                // }


        return null;
    }

    private Point findMin

}
