package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;
import org.w3c.dom.html.HTMLImageElement;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TutoringSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String date;
    private String time;

    private String zoomLink;

    private int totalSpace = 8;
    private boolean valid = true;



    public TutoringSession() {}

    public TutoringSession(String date, String time) {
        this.date = date;
        this.time = time;


    }

    //need a way to generate a zoom link but that will probably be for the service to do.

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
