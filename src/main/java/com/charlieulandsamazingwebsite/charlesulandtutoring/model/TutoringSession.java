package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;

@Entity
public class TutoringSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int year;
    private int month;
    private int day;
    private String time;
    private String description;
    private String zoomLink;
    private boolean visibleToStudents = false;

    @ManyToOne
    private TutoringClass tutoringClass;


    public TutoringSession() {}

    public TutoringSession(TutoringClass tutoringClass, int year, int month, int day) {
        this.tutoringClass = tutoringClass;
        this.year = year;
        this.month = month;
        this.day = day;
    }


    //need a way to generate a zoom link but that will probably be for the service to do.

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id =id;}
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

    public boolean isVisibleToStudents() {
        return visibleToStudents;
    }

    public void setVisibleToStudents(boolean valid) {
        this.visibleToStudents = valid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TutoringClass getTutoringClass() {
        return tutoringClass;
    }
}
