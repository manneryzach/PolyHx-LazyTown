import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;
import java.text.ParseException;

public class MapBuilder {

    public static HashMap<Double[], ArrayList<Road>> parser(String file_path) throws Exception {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("./routes.json")) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray roads = (JSONArray) obj.get("features");

            HashMap<Double[], ArrayList<Road>> network = new HashMap<>();

            for (Object street : roads) {
                JSONObject geometry = (JSONObject) ((JSONObject) street).get("geometry");
                ArrayList<Double[]> coords = (ArrayList<Double[]>) geometry.get("coordinates");


                JSONObject properties = (JSONObject) ((JSONObject) street).get("properties");

                String hood = (String) properties.get("ARR_DRT");

                if (hood.equals("Côte-des-Neiges-Notre-Dame-de-Grâce")){
                    String name = (String) properties.get("NOM_VOIE");
                    Long direction = (Long) properties.get("SENS_CIR");

                    Random random = new Random();
                    int road_speed = (int) random.nextGaussian() * 15 + 45;
                    if (road_speed > 65) {
                        road_speed = 65;
                    }
                    if (road_speed < 25) {
                        road_speed = 25;
                    }

                    Road road = new Road(name, coords, direction, road_speed);

                    for (Double[] elem : coords) {
                        if (network.get(elem) == null) {
                            ArrayList<Road> hash_roads = new ArrayList<>();
                            hash_roads.add(road);
                            network.put(elem, hash_roads);
                        } else if (network.get(elem) != null) {
                            network.get(elem).add(road);
                        }
                    }
                }
            }

            return network;
        }
    }
}
