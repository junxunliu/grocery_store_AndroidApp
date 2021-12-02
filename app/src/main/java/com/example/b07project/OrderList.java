package com.example.b07project;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;

public class OrderList {

    private ArrayList<Order> orderList = new ArrayList<Order>();

    public OrderList() { }

    public OrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<Order> getList(){
        return orderList;
    }

    public void addOrder(Order order){
        orderList.add(order);
    }

    public OrderList search(User user){
        OrderList list = new OrderList();
        String type = user.getUserType();
        if(type.equals("Customer")){
            for(Order o:orderList) {
                if(user.getUserId().equals(o.getCustomerId())){
                    list.addOrder(o);
                }
            }
        }
        else if (type.equals("Store Owner")) {
            for (Order o :orderList) {
                if(user.getStoreName().equals(o.getStoreName())){
                    list.addOrder(o);
                }
            }
        }
        return list;
    }

/*
    public String displayCustomerOrder(User user){
        String display = "";
        for(Order o:orderList){
            display = ("Store Info: " + o.getStoreName() + "\n"
                    + "Customer Info: " + user.getFirstName() + " " + user.getLastName() + "\n"
                    + "Current Status: " + o.getStatus() + "\n");
            for(OrderedProduct p:o.getOrder()){
                display = (display + "   - " + p.name + " $" + p.price + " * " + p.quantity + "\n");
            }
        }
        return display;
    }

    public String displayStoreOrder(User user){
        String display = "";
        for(Order o:orderList){
            display = ("Store Info: " + user.getStoreName() + "\n"
                    + "Customer Info: " + o.getCustomerName() + "\n"
                    + "Current Status: " + o.getStatus() + "\n");
            for(OrderedProduct p:o.getOrder()){
                display = (display + "   - " + p.name + " $" + p.price + " * " + p.quantity + "\n");
            }
        }
        return display;
    }*/

    @Override
    public String toString(){
        String display = "";
        for(Order o:orderList){
            display = display + o.toString();
        }
        return display;
    }
}