package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringSessionService;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//students will buy from a tutoring class not each session
@Entity
public class TutoringClass {
    //change everything to DateTimes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subject;
    private int year;
    private int month;
    private String day;
    private int time;
    private String timeSuffix;
    private int duration;

    public String getFontAwesomeClass() {
        return fontAwesomeClass;
    }

    public void setFontAwesomeClass(String fontAwesomeClass) {
        this.fontAwesomeClass = fontAwesomeClass;
    }

    private String fontAwesomeClass;


    private Integer pricePerHour;
    private int totalSpace = 30;

    //when i make a tutoring class i can just make the dependent session with it.

    public TutoringClass() {

    }

    public List<TutoringSession> createSessions() {
        //first, find the dates that will be available.
        LocalDate date = LocalDate.of(year, month, 1);
        List<TutoringSession> res = new ArrayList<>();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int daysOfMonth = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> 28;
            default -> 30;
        };
        for(int i = 1; i <= daysOfMonth; i++) {
            if(LocalDate.of(year, month, i).getDayOfWeek().toString().equals(day.toUpperCase())) {
                res.add( new TutoringSession(this, year, month, i));
            }
        }

        //create a session for those days at that time.
        return res;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    public String getDates(TutoringSessionService tss) {
        String res = "";
        List<TutoringSession> tutoringSessions = tss.getSessionsFromClass(this);

        for(TutoringSession session : tutoringSessions) {
            res += session.getMonth() + "/" + session.getDay() + ", ";
        }
        res = res.substring(0, res.length()-2);

        return res;
    }

    public String getTimeXtoX() {

        int startTime = this.time;
        int endTime = this.time + this.duration;
        String res = startTime + timeSuffix + endTime;
        if(startTime < 12 && endTime > 12) {
            res += "pm";
        } else {
            res += timeSuffix;
        }

        return res;
    }


}
