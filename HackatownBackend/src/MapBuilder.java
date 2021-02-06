import DataTypes.Point;
import DataTypes.Road;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;

public class MapBuilder {

    public static HashMap<Point, ArrayList<Road>> parser(String file_path) throws Exception {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(file_path)) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray roads = (JSONArray) obj.get("features");

            HashMap<Point, ArrayList<Road>> network = new HashMap<>();

            for (Object street : roads) {
                JSONObject geometry = (JSONObject) ((JSONObject) street).get("geometry");
                JSONArray coords = (JSONArray) geometry.get("coordinates");

                JSONObject properties = (JSONObject) ((JSONObject) street).get("properties");

                String hood = (String) properties.get("ARR_GCH");

                if (hood.equals("Côte-des-Neiges-Notre-Dame-de-Grâce") || hood.equals("Westmount") || hood.equals("Ville-Marie") || hood.equals("Le Sud-Ouest")) {
                    String name = (String) properties.get("NOM_VOIE");
                    Long direction = (Long) properties.get("SENS_CIR");

                    Random random = new Random();
                    int road_speed = (int) (random.nextGaussian() * 15 + 45);
                    if (road_speed > 65) road_speed = 65;
                    if (road_speed < 25) road_speed = 25;


                    // Update road coordinates in next for loop instead
                    Road road = new Road(name, direction, road_speed);

                    Iterator iter = coords.iterator();

                    Object p0_temp =  iter.next();
                    Point p0 = new Point((Double) ((JSONArray) p0_temp).get(0), (Double) ((JSONArray) p0_temp).get(1));

                    Object p1_temp = iter.next();
                    Point p1 = new Point((Double) ((JSONArray) p1_temp).get(0), (Double) ((JSONArray) p1_temp).get(1));

                    while(iter.hasNext()) {
                        if (direction >= 0) {
                            if (network.get(p0) == null) {
                                ArrayList<Road> hash_roads = new ArrayList<>();
                                hash_roads.add(new Road(name, p0, p1, road_speed));
                                network.put(p0, hash_roads);
                            } else network.get(p0).add(new Road(name, p0, p1, road_speed));
                        }
                        if (direction <= 0) {
                            if (network.get(p1) == null) {
                                ArrayList<Road> hash_roads = new ArrayList<>();
                                hash_roads.add(new Road(name, p1, p0, road_speed));
                                network.put(p1, hash_roads);
                            } else network.get(p1).add(new Road(name, p1, p0, road_speed));
                        }
                        // Update pointers
                        DataTypes.Point temp = new DataTypes.Point(p1.x, p1.y);
                        p1_temp = iter.next();
                        p1 = new Point((Double) ((JSONArray) p1_temp).get(0), (Double) ((JSONArray) p1_temp).get(1));
                        p0 = temp;
                    }

                    for (Object elem : coords) {
                        Point p = new Point((Double) ((JSONArray) elem).get(0), (Double) ((JSONArray) elem).get(1));
                        road.getCoordinates().add(p);

                        if (network.get(p) == null) {
                            ArrayList<Road> hash_roads = new ArrayList<>();
                            hash_roads.add(road);
                            network.put(p, hash_roads);
                        } else network.get(p).add(road);
                    }
                }
            }
            return network;
        }
    }
}
