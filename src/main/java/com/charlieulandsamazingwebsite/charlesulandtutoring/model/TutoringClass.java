package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringSessionService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//students will buy from a tutoring class not each session
@Setter
@Getter
@Entity
public class TutoringClass {
    //change everything to DateTimes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subject;
    private int year = 2025;
    private int month = 9;
    private String day;
    private int time = 6;
    private String timeSuffix = "pm";
    private int duration = 2;


    private String fontAwesomeClass;


    private int pricePerHour = 20;
    private int totalSpace = 8;

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

    public String getDates(TutoringSessionService tss) {
        List<TutoringSession> tutoringSessions = tss.getSessionsFromClass(this);
        StringBuilder res = new StringBuilder();

        for (TutoringSession session : tutoringSessions) {
            res.append(session.getMonth())
                    .append("/")
                    .append(session.getDay())
                    .append(", ");
        }

        if (!res.isEmpty()) {
            res.setLength(res.length() - 2); // remove last ", "
        }
        return res.toString();
    }

    public String getTimeXtoX() {

        int startTime = this.time;
        int endTime = this.time + this.duration;
        String res = startTime + timeSuffix + "-" + endTime;
        if(startTime < 12 && endTime > 12) {
            res += "pm";
        } else {
            res += timeSuffix;
        }

        return res;
    }


}
