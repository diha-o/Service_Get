package dd_pc.service;

/**
 * Created by dd on 27.10.2014.
 */
public class temp_class_response {
    String status;
    String code;
    String monitor_frequency;
     public temp_class_response ( String status,
             String code,
             String monitor_frequency){
         this.status=status;
         this.code=code;
         this.monitor_frequency=monitor_frequency;
     }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMonitor_frequency() {
        return monitor_frequency;
    }

    public void setMonitor_frequency(String monitor_frequency) {
        this.monitor_frequency = monitor_frequency;
    }
}
