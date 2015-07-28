package dd_pc.service;

/**
 * Created by dd-pc on 25.09.2014.
 */
public class temp_class {
        private String id;
        private String to;
        private String from;
        private String data;
        private String date;


        public temp_class(String id, String date, String to, String from, String data) {
            this.id = id;
            this.date=date;
            this.from = from;
            this.to = to;
            this.data = data;

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

