package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne
    private Student student;

    @ManyToOne
    private TutoringClass tutoringClass;

    private Long totalAmount;
    public Order() {
        orderDate = LocalDateTime.now();
    }
}

