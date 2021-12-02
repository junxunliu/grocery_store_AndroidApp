package com.example.b07project;

public class OrderedProduct extends Product{
    int quantity;

    public OrderedProduct() {

    }

    public OrderedProduct(String brand,String name,String price,int quantity){
        super(name,brand,price);
        this.quantity=quantity;
    }

    @Override
    public String toString() {
        return brand + ":" + name + ":" + price + ":" + quantity;
    }
}
