package openhome.model;

import java.util.Date;

public class Blah {
    private String username;
    private Date date;
    private String txt;

    public Blah(){

    }

    public Blah(String username, Date date, String txt) {
        this.username = username;
        this.date = date;
        this.txt = txt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "Blah{" + "username=" + username + ", date=" + date + ", txt=" + txt + '}';
    }
}
