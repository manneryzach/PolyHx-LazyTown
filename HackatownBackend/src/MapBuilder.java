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
                //System.out.println(coords.get(0) instanceof Double[]);

                JSONObject properties = (JSONObject) ((JSONObject) street).get("properties");

                String hood = (String) properties.get("ARR_DRT");

                if (hood.equals("Côte-des-Neiges-Notre-Dame-de-Grâce")) {
                    String name = (String) properties.get("NOM_VOIE");
                    Long direction = (Long) properties.get("SENS_CIR");

                    Random random = new Random();
                    int road_speed = (int) (random.nextGaussian() * 15 + 45);
                    if (road_speed > 65) {
                        road_speed = 65;
                    }
                    if (road_speed < 25) {
                        road_speed = 25;
                    }

                    // Update road coordinates in next for loop instead
                    Road road = new Road(name, direction, road_speed);

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
