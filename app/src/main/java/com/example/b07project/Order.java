package com.example.b07project;

import java.util.HashSet;

public class Order {
    StoreOwner s;
    Customer c;
    HashSet<OrderedProduct> order = new HashSet<OrderedProduct>();
    boolean status;

    public Order(){ }

    public Order(StoreOwner s, Customer c, HashSet<OrderedProduct> order){
        this.s = s;
        this.c = c;
        this.order = order;
        this.status = false;
    }

    public void addOrderedProduct(OrderedProduct p){
        order.add(p);
    }

    public StoreOwner getStore(){
        return s;
    }

    public Customer getCustomer(){
        return c;
    }

    public boolean getStatus(){
        return status;
    }

    public void changeStatus(boolean s){
        status = s;
    }

    @Override
    public String toString(){
        String display = "";
        display = ("Store Info: " + s.getStoreName() + "\n"
                + "Customer Info: " + c.getFirstName() + " " + c.getLastName() + "\n"
                + "Ordered Product: " + "\n");
        for(OrderedProduct p:order){
            display = (display + "  " + "product: " + p.name + "    "
                    + "quantity: " + p.quantity + " " + "\n");
        }
        String s = "";
        if(status == false) s = "incomplete";
        else s = "complete";
        display = display + "Current Status: " + s + "\n";
        return display;
    }
}