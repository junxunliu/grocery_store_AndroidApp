package com.example.b07project;

public class Customer extends User{
    public Customer(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }

    public Customer() {

    }

    @Override
    public String toString() {
        return this.getFirstName() + this.getLastName();
    }
}
