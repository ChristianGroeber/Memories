/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author chris
 */
public class MyImage {

    private Image image;
    private String gps;
    private String location;
    private String temperature;
    private static String apiKey = new ApiKey().getKey();
    private Date date;

    public MyImage(Image image, String gps, String location, String temperature) {
        this.image = image;
        this.gps = gps;
        this.location = location;
        this.temperature = temperature;
    }

    public MyImage() {
        Location gps = LocationManager.getLocationManager().getCurrentLocationSync();
        Coord coord = new Coord(gps.getLatitude(), gps.getLongitude());
        location = getFormattedAddress(coord);

        System.out.println(gps);
    }

    ;
    
    public static String getFormattedAddress(Coord coord) {
        String ret = "";
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", apiKey);
            request.addArgument("latlng", coord.getLatitude() + "," + coord.getLongitude());

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    ret = (String) ((LinkedHashMap) results.get(0)).get("formatted_address");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    
    
    private final SimpleDateFormat sd = new SimpleDateFormat("hh:mm");
    @Override
    public String toString() {
        String str = "";
        str += image.toString() + "\\";
        str += sd.format(date) + "\\";
        str += gps + "\\";
        str += location + "\\";
        str += temperature;
        return str;
    }

    public MyImage fromString(String myImage) throws IOException, ParseException {
        StringTokenizer tokenizer = new StringTokenizer(myImage, "\\");
        String strImage = tokenizer.nextToken();
        String strDate = tokenizer.nextToken();
        gps = tokenizer.nextToken();
        location = tokenizer.nextToken();
        temperature = tokenizer.nextToken();
        this.image = Image.createImage(strImage);
        this.date = sd.parse(strDate);
        return this;
    }

}
