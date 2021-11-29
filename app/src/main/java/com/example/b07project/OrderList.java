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

public class OrderList {

    private ArrayList<Order> orderList = new ArrayList<Order>();

    public OrderList() { }

    public void addOrder(Order order){
        orderList.add(order);
    }

    public ArrayList<Order> search(User user){
        ArrayList<Order> storeOrderList = new ArrayList<Order>();
        String type = user.getUserType();
        if(type.equals("Customer")){
            for(Order o:orderList) {
                if ((Customer) user == o.getCustomer()) {
                    storeOrderList.add(o);
                }
            }
        }
        else if (type.equals("Store Owner")) {
            for (Order o : orderList) {
                if ((StoreOwner) user == o.getStore()) {
                    storeOrderList.add(o);
                }
            }
        }
        return storeOrderList;
    }

    /*public ArrayList<Order> search_by_customer(Customer c){
        ArrayList<Order> customerOrderList = new ArrayList<Order>();
        for(Order o:orderList){
            if(o.getCustomer() == c){
                customerOrderList.add(o);
            }
        }
        return customerOrderList;
    }*/

    public void readFromDB(){
        /*FirebaseDatabase.getInstance().getReference().child("OrderList").child("StoreOwner").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });*/
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