import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

// import org.apache.commons.lang3.ArrayUtils.*;

public class MapBuilder {
    public static void main(String[] args) {
        try {
            ArrayList<Street> montreal = GeoJSONParser();
            for (Street street : montreal) {
                System.out.println(street.Name);
            }

        } catch (Exception e) {
            System.out.println("Tough luck!");
        }

    }

    static ArrayList<Street> GeoJSONParser() throws Exception {
        JSONParser parser = new JSONParser();
        ArrayList<Street> network = new ArrayList<>();

        try (Reader reader = new FileReader("./routes.json")) {
            JSONObject obj = (JSONObject) parser.parse(reader);
            JSONArray streets = (JSONArray) obj.get("features");


            for (Object street : streets) {
                //JSONObject street = (JSONObject) streets.get(i);

                JSONObject geometry = (JSONObject) ((JSONObject) street).get("geometry");
                ArrayList<Double[]> coords = (ArrayList<Double[]>) geometry.get("coordinates");
                // System.out.println(coords);

                JSONObject properties = (JSONObject) ((JSONObject) street).get("properties");
                String name = (String) properties.get("NOM_VOIE");
                Long direction = (Long) properties.get("SENS_CIR");

                Street street_obj = new Street(name, direction, coords);
                network.add(street_obj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return network;
    }
}

class Street {
    public String Name;
    public Long Direction;
    public ArrayList<Double[]> Coordinates;

    public Street(String name, Long direction, ArrayList<Double[]> coordinates) {
        Name = name;
        Direction = direction;
        Coordinates = coordinates;
    }
}
