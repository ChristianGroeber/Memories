/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Dialog;
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
import org.littlemonkey.connectivity.Connectivity;

/**
 *
 * @author chris
 */
public class MyImage extends Memories {

    private String imagePath;
    private String gps;
    private String location;
    private String temperature;
    private static String apiKey = new ApiKey().getKey();
    private Date date;

    public MyImage(String imagePath, String gps, String location) {
        this.imagePath = imagePath;
        this.gps = gps;
        this.location = location;
    }

    public MyImage() {
        Location gps = LocationManager.getLocationManager().getCurrentLocationSync();
        Coord coord = new Coord(gps.getLatitude(), gps.getLongitude());
        location = getFormattedAddress(coord);
        date = new Date();
    }

    public static String getFormattedAddress(Coord coord) {
        String ret = "";
        if (Connectivity.isConnected()) {
            try {
                ConnectionRequest request = null;
                Map<String, Object> response = null;
                request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
                request.addArgument("key", apiKey);
                request.addArgument("latlng", coord.getLatitude() + "," + coord.getLongitude());

                NetworkManager.getInstance().addToQueueAndWait(request);
                response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
                if (response.get("results") != null) {
                    ArrayList results = (ArrayList) response.get("results");
                    if (results.size() > 0) {
                        ret = (String) ((LinkedHashMap) results.get(0)).get("formatted_address");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        } else {
            Dialog.show("Error while getting Location", "I couldn't connect to Google Maps. Make sure you have a working Internet connection.", "OK", null);
        }
        return ret;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
    private final String spl = ";;";

    @Override
    public String toString() {
        String str = "";
        try {
            str += imagePath + spl;
            str += sd.format(date) + spl;
            str += gps + spl;
            str += location + spl;
            str += temperature + spl;
        } catch (Exception e) {
            if (super.isDev()) {
                Dialog.show("Error toString", "Error in image tostring method:\n" + e.toString(), "OK", null);
            }
        }
        return str;
    }

    public MyImage fromString(String myImage) throws IOException, ParseException {
        StringTokenizer tokenizer = new StringTokenizer(myImage, spl);
        imagePath = tokenizer.nextToken();
        try {
            String strDate = tokenizer.nextToken();
            gps = tokenizer.nextToken();
            location = tokenizer.nextToken();
            temperature = tokenizer.nextToken();
            this.date = sd.parse(strDate);
        } catch (Exception e) {
            if (super.isDev()) {
                Dialog.show("Error", "Error in From String method:\n" + e.toString(), "OK", null);
            }
        }
        return this;
    }

    public ArrayList<String> toArray() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add(imagePath);
        arr.add(sd.format(date));
        arr.add(gps);
        arr.add(location);
        return arr;
    }

}
