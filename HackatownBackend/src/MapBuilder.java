import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;
import java.text.ParseException;

import Road;

public class MapBuilder {
    public static ArrayList<Road> parser(String file_path) throws exception{
        JSONParser parser = new JSONParser();
        ArrayList<Road> network = new ArrayList<Road>();

        try(Reader reader = new FileReader("./routes.json")){
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray roads = (JSONArray) obj.get("features");

            ArrayList<Double[]> all_coords = new ArrayList<Double[]>();
            ArrayList<double[]> all_coords_purged = new ArrayList<Double[]>();

            for (Object street : roads){
                JSONObject geometry = (JSONObject) ((JSONObject) street).get("geometry");
                ArrayList<Double[]> coords = (ArrayList<Double[]>) geometry.get("coordinates");
                for (Double[] elem : coords){
                    all_coords.add(elem);
                }

                JSONObject properties = (JSONObject) ((JSONObject) street).get("properties");

                String name = (String) properties.get("NOM_VOIE");
                Long direction = (Long) properties.get("SENS_CIR");

                Random random = new Random();
                int road_speed = random.nextGaussian()*15+45;
                if (road_speed > 65){
                    road_speed = 65;
                }if (road_speed < 25){
                    road_speed = 25;
                }

                //

                //
                //

            }
    }
}
