package com.example.b07project;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String firstName;
    private String lastName;
    private String storeName;
    private String storeAddress;
    private String userType;
    private String userId;

    public User(String email, String firstName, String lastName) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String firstName, String lastName, String storeName, String storeAddress) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public User() { }


    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public void setUserType(String userType) { this.userType = userType; }

    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
