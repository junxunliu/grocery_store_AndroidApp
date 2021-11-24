package com.example.b07project;

public class OrderedProduct extends Product{
    int quantity;

    public OrderedProduct(String name,String brand,double price,int quantity){
        super(name,brand,price);
        this.quantity=quantity;
    }

    @Override
    public String toString() {
        return brand + ":" + name + ":" + price + ":" + quantity;
    }
}
