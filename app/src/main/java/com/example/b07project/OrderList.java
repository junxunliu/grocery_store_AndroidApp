package com.example.b07project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderList {

    private ArrayList<Order> orderList = new ArrayList<Order>();

    public OrderList() { }

    public void addOrder(Order order){
        orderList.add(order);
    }

    public ArrayList<Order> search_by_store(StoreOwner s){
        ArrayList<Order> storeOrderList = new ArrayList<Order>();
        for(Order o:orderList){
            if(o.getStore() == s){
                storeOrderList.add(o);
            }
        }
        return storeOrderList;
    }

    public ArrayList<Order> search_by_customer(Customer c){
        ArrayList<Order> customerOrderList = new ArrayList<Order>();
        for(Order o:orderList){
            if(o.getCustomer() == c){
                customerOrderList.add(o);
            }
        }
        return customerOrderList;
    }

    public void readFromDB(){

    }

    public void updateDB(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        for(Order o:orderList) {
            db.child("OrderList").child(String.valueOf(orderList.indexOf(o))).setValue(o);
        }
    }

    @Override
    public String toString(){
        String display = "";
        for(Order order:orderList){
            display = (display + "ORDER " + String.valueOf(orderList.indexOf(order)) + "\n"
                    + order.toString() + "\n\n");
        }
        return display;
    }
}