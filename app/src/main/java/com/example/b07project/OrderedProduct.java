package com.example.b07project;

import java.io.Serializable;

public class OrderedProduct extends Product implements Serializable {
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
