package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;

@Entity
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;
    private Integer subtotal;
    private Integer total;
    private String paymentType;
    private String paymentStatus;
    @ManyToOne
    @JoinColumn(name = "tutoring_class_id")
    private TutoringClass tutoringClass;
    String description;

    protected Order() {}
    public Order(User user, int subtotal, int total, String paymentType, String paymentStatus, TutoringClass tutoringClass, String description) {
        this.user = user;
        this.subtotal = subtotal;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.tutoringClass = tutoringClass;
        this.description = description;
    }

    public TutoringClass getTutoringClass() {
        return tutoringClass;
    }

    public void setTutoringClass(TutoringClass tutoringClass) {
        this.tutoringClass = tutoringClass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
