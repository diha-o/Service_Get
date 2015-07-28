package dd_pc.service;

/**
 * Created by dd on 17.10.2014.
 */
public class temp_class_gps_wifi {
    String gps;String wifi;
    String wifi_mac;
    public temp_class_gps_wifi (String gps,String wifi,
            String wifi_mac ){
        this.gps=gps;  this.wifi_mac=wifi_mac;
        this.wifi=wifi;

    }

    public String getGps() {
        return gps;
    }
    public String getWifi() {
        return wifi;
    }
    public String getWifi_mac() {
        return wifi_mac;
    }
    public void setGps(String gps) {
        this.gps = gps;
    }

    public void setWifi_mac(String wifi_mac) {
        this.wifi_mac = wifi_mac;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }



}
