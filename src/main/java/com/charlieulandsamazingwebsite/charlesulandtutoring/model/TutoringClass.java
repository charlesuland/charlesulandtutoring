package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TutoringClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int pricePerHour = 2000;



    private String month;

    protected TutoringClass() {}

    public TutoringClass(String name, String month) {
        this.name = name;
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }



    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
