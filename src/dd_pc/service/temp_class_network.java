package dd_pc.service;

/**
 * Created by dd on 19.10.2014.
 */
public class temp_class_network {
    String simid;
    String tel_e_mail;
    String timezone;
    public  temp_class_network (    String simid,
            String tel_e_mail,
            String timezone){
        this.simid = simid;
        this.tel_e_mail = tel_e_mail;
        this.timezone = timezone;

    }

    public void setSimid(String simid) {
        this.simid = simid;
    }

    public void setTel_e_mail(String tel_e_mail) {
        this.tel_e_mail = tel_e_mail;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getSimid() {
        return simid;
    }

    public String getTel_e_mail() {
        return tel_e_mail;
    }

    public String getTimezone() {
        return timezone;
    }
}
