package dd_pc.service;

/**
 * Created by dd-pc on 06.10.2014.
 */
public class temp_class_helth {
    private String cpuusage;
    private String batterylevel;
    private String ramusage;
    private String charge_state;



    public temp_class_helth(String cpuusage,String batterylevel,String charge_state, String ramusage) {
        this.cpuusage = cpuusage;
        this.batterylevel = batterylevel;
        this.ramusage = ramusage;
        this.charge_state = charge_state;




    }

    public void setCpuusage(String cpuusage) {
        this.cpuusage = cpuusage;
    }
    public String getCpuusage() {
        return cpuusage;
    }
    public String getBatterylevel() {
        return batterylevel;
    }
    public void setBatterylevel(String batterylevel) {
        this.batterylevel = batterylevel;
    }
    public String getRamusage() {
        return ramusage;
    }
    public void setRamusage(String ramusage) {
        this.ramusage = ramusage;
    }
    public void setCharge_state(String charge_state) {
        this.charge_state = charge_state;
    }
    public String getCharge_state() {
        return charge_state;
    }




}
