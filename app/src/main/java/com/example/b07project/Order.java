package com.example.b07project;

import java.io.Serializable;
import java.util.HashSet;

public class Order implements Serializable {
    private StoreOwner s;
    private Customer c;
    private HashSet<OrderedProduct> order = new HashSet<OrderedProduct>();
    private boolean status;

    public Order(){ }

    public Order(StoreOwner s, Customer c, HashSet<OrderedProduct> order){
        this.s = s;
        this.c = c;
        this.order = order;
        this.status = false;
    }

    public StoreOwner getStore(){
        return s;
    }

    public Customer getCustomer(){
        return c;
    }

    public HashSet<OrderedProduct> getOrder(){ return order; }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean s){
        status = s;
    }

    public void addOrderedProduct(OrderedProduct p){
        order.add(p);
    }

    public String displayStatus(){
        String s = "";
        if(status) s = "Complete";
        else s = "Incomplete";
        return s;
    }

    @Override
    public String toString(){
        String display = "";
        display = ("Store Info: " + s.getStoreName() + "\n"
                + "Customer Info: " + c.getFirstName() + " " + c.getLastName() + "\n");
        String s = "";
        if(status == false) s = "Incomplete";
        else s = "Complete";
        display = display + "Current Status: " + s + "\n";
        display = display + "Ordered Product: " + "\n";
        for(OrderedProduct p:order){
            display = (display + "   - " + p.name + " * " + p.quantity + "\n");
        }
        return display;
    }
}