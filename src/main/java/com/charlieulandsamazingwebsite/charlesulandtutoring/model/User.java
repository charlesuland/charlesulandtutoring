package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String hashPassword;
    private String dateJoined;
    private String role = "student";
    private String yearInSchool;

    public User() {}

    public User(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.email = email;
        this.phoneNumber = phoneNumber;
        //update password
        this.hashPassword = password;
        this.dateJoined = "1";
        this.yearInSchool = yearInSchool;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getYearInSchool() {
        return yearInSchool;
    }

    public void setYearInSchool(String yearInSchool) {
        this.yearInSchool = yearInSchool;
    }
}
