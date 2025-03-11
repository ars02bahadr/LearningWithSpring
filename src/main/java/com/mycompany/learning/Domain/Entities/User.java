package com.mycompany.learning.Domain.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 500, name = "first_name")
    private String firstName;
    @Column(nullable = false, length = 500, name = "last_name")
    private String lastName;
    @Column(nullable = false, length = 500, name = "email")
    private String email;
    @Column(nullable = false, length = 500, name = "password")
    private String password;
    @Column(nullable = true, length = 500, name = "profile_picture_url")
    private String profilePictureUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
