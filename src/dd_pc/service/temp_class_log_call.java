package dd_pc.service;

/**
 * Created by dd-pc on 25.09.2014.
 */
public class temp_class_log_call {
    private String num;
    private String data;
    private String line;



    public temp_class_log_call(String num, String data,String line) {
        this.num = num;
        this.data = data;
        this.line = line;

    }



    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}