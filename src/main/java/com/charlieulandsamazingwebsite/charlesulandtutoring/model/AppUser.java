package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "app_user")
public class AppUser {

    //maybe name this student
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq")
    @SequenceGenerator(name = "app_user_seq", sequenceName = "app_user_seq", allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String hashPassword;
    private String dateJoined;
    private String role = "STUDENT";

    //change the string to a local date
    public AppUser() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        this.dateJoined = date.format(formatter);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashPassword(String password) {
        this.hashPassword = password;
    }
    public String getHashPassword() {
        return hashPassword;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
