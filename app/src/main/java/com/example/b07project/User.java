package com.example.b07project;

public class User {
    public String email;
    public String firstName;
    public String lastName;
    public String userType;
    public String userId;

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

    public String getId() {
        return userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }
}
