package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private TutoringClass tutoringClass;

    private LocalDate enrollmentDate;


    public Enrollment() {
        enrollmentDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public TutoringClass getTutoringClass() {
        return tutoringClass;
    }

    public void setTutoringClass(TutoringClass tutoringClass) {
        this.tutoringClass = tutoringClass;
    }
}
