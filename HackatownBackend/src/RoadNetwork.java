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

    public static void MinHeapify(ArrayList<Point> points, int i) {
        int l = 2 * i;
        int r = 2 * i + 1;
        int n = points.size();
        int smallest;
        if (l < n && points.get(l).getDist() < points.get(i).getDist())
            smallest = l;
        else
            smallest = i;
        if (r < n && points.get(r).getDist() < points.get(smallest).getDist())
            smallest = r;
        if (smallest != i) {
            Sorting.swap(points, i, smallest);
            MinHeapify(points, smallest);
        }

    }

    /**
     * Computes the shortest weighted path from one road intersection to another using Dijkstra's algorithm.
     * The weight of each road is calculated with respect its the speed.
     *
     * @param coordA Starting point
     * @param coordB End point
     * @return A sequence of intersection representing the optimal route.
     **/
    public ArrayList<Point> shortestPathNoHeap(Point coordA, Point coordB) {
        if (!roads.containsKey(coordA) || !roads.containsKey(coordB))
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

    public ArrayList<Point> shortestPath(Point coordA, Point coordB) {
        if (!roads.containsKey(coordA) || !roads.containsKey(coordB))
            throw new IllegalArgumentException("Coordinates must be intersections on the map");


        System.out.println("finding shortest path");
        ArrayList<Point> coords = new ArrayList<>();

        // coords.add(new Point());
        coords.addAll(roads.keySet());
        Sorting.swap(coords, 1, coords.indexOf(coordA));
        // Set all vertices dist to infty

        coords.get(1).setDist(0.);
        Point endCoords = new Point();

        // Heap-ify array
        //for (int i = 1; i < coords.size() / 2; i++) MinHeapify(coords, i);

        while (!coords.isEmpty()) {
            // Remove point u from heap (slow but can be faster using avl)
            Point u = findMin(coords);
            coords.remove(u);

            // Swap last and remove u
            // coords.set(1, coords.get(coords.size() - 1));
            // coords.remove(coords.size() - 1);
            // MinHeapify(coords, 1);

            if (u.equals(coordB)) {
                endCoords = u;
                break;
            }

            if(roads.get(u) != null) {
                for (Road outRoad : roads.get(u)) {
                    // Only visit new nodes
                    Point v = outRoad.getNextPoint();
                    if (coords.contains(v)) {
                        Double alt = u.getDist() + outRoad.getWeight();

                        if (alt < v.getDist()) {
                            v.setDist(alt);
                            v.setPrev(u);
                        }
                        System.out.println(v);
                    }
                }
            }
            System.out.println(coords.size());
        }

        System.out.println("Backtracking...");

        LinkedList<Point> route = new LinkedList<>();
        Point u = endCoords;
        System.out.println(endCoords);
        if (u.getPrev() != null || u.equals(coordB)) {
            while (u != null) {
                route.addFirst(u);
                u = u.getPrev();
            }
        }
        System.out.println(new ArrayList<>(route));
        return new ArrayList<>(route);

    }

    private Point findMin(ArrayList<Point> coords) {
        Point min = new Point();
        min.setDist(-1.);

        for (Point p : coords) {
            if (min.getDist() == -1 ||  p.getDist() < min.getDist())
                min = p;
        }
        return min;
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
