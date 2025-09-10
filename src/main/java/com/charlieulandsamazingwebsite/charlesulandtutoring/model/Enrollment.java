package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private TutoringClass tutoringClass;

    private LocalDate enrollmentDate;


    public Enrollment() {
        enrollmentDate = LocalDate.now();
    }


}
