package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
    private String zoomPassword;

    @ManyToOne
    private TutoringClass tutoringClass;


    public TutoringSession() {}

    public TutoringSession(TutoringClass tutoringClass, int year, int month, int i) {
        this.tutoringClass = tutoringClass;
        this.year = year;
        this.month = month;
        this.day = i;
    }
}
