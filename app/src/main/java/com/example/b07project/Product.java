package com.example.b07project;

public class Product {
    String name;
    String brand;
    double price;


    public Product(){ }
    public Product(String name,String brand,double price){
        this.name=name;
        this.brand=brand;
        this.price=price;
    }

    @Override
    public String toString() {
        return brand + ":" + name + ":" + price;
    }
}
