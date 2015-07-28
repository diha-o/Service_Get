package dd_pc.service;

/**
 * Created by dd-pc on 26.09.2014.
 */
public class temp_class_geodata {
    private String Longitude;
    private String Latitude;



    public temp_class_geodata(String Longitude, String Latitude) {
        this.Longitude = Longitude;
        this.Latitude= Latitude;

    }



    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }


    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }
}

