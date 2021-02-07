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
    public ArrayList<Road> getRoads(Point coordinates) {
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

        PriorityQueue<Node> vertices = new PriorityQueue<>();
        HashMap<Point, Node> pointDict = new HashMap<>();

        for (Point v : roads.keySet())
            if (!v.equals(coordA)) {
                Node node = new Node(v);
                vertices.add(node);
                pointDict.put(v, node);
            }


        Node temp = new Node(coordA);
        temp.setDist(0.0);
        pointDict.put(coordA, temp);
        vertices.add(temp);

//        for (Node u : vertices) {
//            if (u.equals(coordA)) break;
//
//            for (Road outRoad : roads.get(u)) {
//                Double alt = u.getDist() + outRoad.getWeight();
//                if (alt < outRoad.getNextPoint().getDist()) {
//                    outRoad.getNextPoint().setDist(alt);
//                    outRoad.getNextPoint().setPrev(u);
//                }
//        }

        while (!vertices.isEmpty()) {
            Node u = vertices.poll();

            vertices.remove(u);
            if (u.p.equals(coordB)) break;

            for (Road outRoad : roads.get(u.p)) {
                Double alt = u.getDist() + outRoad.getWeight();

                if (alt < pointDict.get(outRoad.getNextPoint()).getDist()) {
                    pointDict.get(outRoad.getNextPoint()).setDist(alt);
                    pointDict.get(outRoad.getNextPoint()).setPrev(u);
                }
            }
        }

        LinkedList<Point> route = new LinkedList<>();
        Node u = pointDict.get(coordB);
        while (u.getPrev() != null && !u.p.equals(coordA)) {
            route.addFirst(u.p);
            u = u.getPrev();
        }

        return new ArrayList<>(route);
    }

    private class Node implements Comparable {
        private Double dist;
        private Node prev;
        private Point p;

        public Node(Point p) {
            this.dist = Double.MAX_VALUE;
            this.prev = null;
            this.p = p;
        }

        public Double getDist() {
            return dist;
        }

        public Node getPrev() {
            return prev;
        }

        public Point getP() {
            return p;
        }

        public void setDist(Double dist) {
            this.dist = dist;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setP(Point p) {
            this.p = p;
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Node)) throw new IllegalArgumentException();
            if (this.dist - ((Node) o).dist < 0) return -1;
            if (this.dist - ((Node) o).dist > 0) return 1;
            return 0;
        }
    }

}
