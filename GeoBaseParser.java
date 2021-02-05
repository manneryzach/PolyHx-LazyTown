import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;
import java.text.ParseException;

public class GeoBaseParser{
    public static ArrayList<Road> parser(String file) throws exception{
        JSONParser parser = new JSONParser();
        ArrayList<Road> network = new ArrayList<Road>();

        try(Reader reader = new FileReader("./routes.json")){
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray roads = (JSONArray) obj.get("features");

            for (Object street : roads){
                JSONObject geometry = (JSONObject) ((JSONObject) street).get("geometry");
                ArrayList<Double[]> coords = (ArrayList<Double[]>) geometry.get("coordinates");

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

            }
        }
    }

}
