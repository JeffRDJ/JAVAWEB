package openhome.model;

import java.util.Date;

public class Blah {
    private String name;
    private Date date;
    private String txt;

    public Blah() {

    }

    public Blah(String name, Date date, String txt) {
        this.name = name;
        this.date = date;
        this.txt = txt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "Blah{" + "username=" + name + ", date=" + date + ", txt=" + txt + '}';
    }
}
