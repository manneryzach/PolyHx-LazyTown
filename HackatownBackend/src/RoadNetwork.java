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
    public ArrayList<Point> shortestPath(Point coordA, Point coordB) {
        if (!roads.containsKey(coordA) || !roads.containsKey(coordB))
            throw new IllegalArgumentException("Coordinates must be intersections on the map");


        ArrayList<Point> coords = new ArrayList<>(roads.keySet());

        Point p0 = coords.get(coords.indexOf(coordA));
        Point p1 = coords.get(coords.indexOf(coordB));
        p0.setDist(0.);

        Sorting.swap(coords, 1, coords.indexOf(p1));

        System.out.println("Coord A: " + coords.get(coords.indexOf(p0)));

        Point endCoords = new Point();

        while (!coords.isEmpty()) {
            Point u = findMin(coords);
            coords.remove(u);

            if (u == p1) {
                endCoords = u;
                System.out.println("Found node!");
                break;
            }

            ArrayList<Road> roadList = roads.get(u);
            for (Road outRoad : roadList) {
                // Only visit new nodes
                Point v = findClosestVertex(outRoad.getNextPoint());
                System.out.println(coords.indexOf(v));
                if (coords.contains(v)) {
                    Double alt = u.getDist() + outRoad.getWeight();

                    if (alt < v.getDist()) {
//                        coords.get(coords.indexOf(v)).setDist(alt);
                        v.setDist(alt);
//                        coords.get(coords.indexOf(v)).setPrev(u);
                        v.setPrev(u);
                    }
                }
            }
        }

            LinkedList<Point> route = new LinkedList<>();
            Point u = p1;
            System.out.println(p1);
            if (u.getPrev() != null || u.equals(coordA)) {
                while (u != null) {
                    route.addFirst(u);
                    u = u.getPrev();
                }
            }
            System.out.println(new ArrayList<>(route));
            return new ArrayList<>(route);


    }

    public ArrayList<Point> shortestPathHeap(Point coordA, Point coordB) {
        if (!roads.containsKey(coordA) || !roads.containsKey(coordB))
            throw new IllegalArgumentException("Coordinates must be intersections on the map");


        System.out.println("finding shortest path");
        coordA.setDist(0.);
        // coords.add(new Point());
        ArrayList<Point> coords = new ArrayList<>(roads.keySet());
        int index = coords.indexOf(coordA);
        // Set all vertices dist to infty
        coords.get(coords.indexOf(coordA)).setDist(0.);

        System.out.println("Coord A: " + coords.get(coords.indexOf(coordA)));
//        coords.get(1).setDist(0.);
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
                System.out.println("Found node!");
                break;
            }

                for (Road outRoad : roads.get(u)) {
                    // Only visit new nodes
                    Point v = outRoad.getNextPoint();
                    if (coords.contains(v)) {
                        Double alt = u.getDist() + outRoad.getWeight();

                        if (alt < v.getDist()) {
                            v.setDist(alt);
                            v.setPrev(u);
                        }
                        // System.out.println(v);
                    }
                }
            //System.out.println(coords.size());
        }

        System.out.println("Backtracking...");

        LinkedList<Point> route = new LinkedList<>();
        Point u = endCoords;
        if (u.getPrev() != null || u.equals(coordB)) {
            while (u != null) {
                route.addFirst(u);
                u = u.getPrev();
            }
        }
        System.out.println(new ArrayList<>(route));
        return new ArrayList<>(route);

    }

    private Point findClosestVertex(Point coord) {
        // Binary search
        int L = 0;
        int R = sortedCoordinates.size() - 1;
        int m = (L + R) / 2;
        double c_norm = Point.eucNorm(coord);
        while (L <= R) {
            m = (L + R) / 2;
            if (Point.eucNorm(sortedCoordinates.get(m)) < c_norm) {
                L = m + 1;
            } else if (Point.eucNorm(sortedCoordinates.get(m)) > c_norm) {
                R = m - 1;
            } else {
                break;
            }
        }
        return sortedCoordinates.get(m);
    }


    private Point findMin(ArrayList<Point> coords) {
        Point min = coords.get(0);
//        min.setDist(-1.);

        for (Point p : coords) {
            if (p.getDist() < min.getDist())
                min = p;
//            else System.out.println(p + " is bigger than " + min);
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
