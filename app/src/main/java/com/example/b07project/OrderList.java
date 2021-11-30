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
                if (user.getFirstName().equals(o.getCustomer().getFirstName())
                        && user.getLastName().equals(o.getCustomer().getLastName())) {
                    list.addOrder(o);
                }
            }
        }
        else if (type.equals("Store Owner")) {
            for (Order o :orderList) {
                StoreOwner st = new StoreOwner();
                st = (StoreOwner) user;
                if (st.getStoreName().equals(o.getStore().getStoreName())) {
                    list.addOrder(o);
                }
            }
        }
        return list;
    }

    /*public OrderList search(User user){
        OrderList list = new OrderList();
        String type = user.getUserType();
        if(type.equals("Customer")){
            for(Order o:orderList) {
                if (user.getFirstName().equals(o.getCustomer().getFirstName())) {
                    list.addOrder(o);
                }
            }
        }
        else if (type.equals("Store Owner")) {
            for (Order o :orderList) {
                if ((StoreOwner) user == o.getStore()) {
                    list.addOrder(o);
                }
            }
        }
        return list;
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

    public void testData(){
        StoreOwner sto = new StoreOwner();
        sto.setStoreName("KFC");
        Customer c = new Customer();
        c.setFirstName("Kitty");
        c.setLastName("Y");
        OrderedProduct p = new OrderedProduct("KFC","chicken","9.99",4);
        HashSet<OrderedProduct> list = new HashSet<>();
        list.add(p);
        Order o = new Order(sto,c,list);
        orderList.add(o);

        StoreOwner sto2 = new StoreOwner();
        sto2.setStoreName("POP EYES");
        Customer c2 = new Customer();
        c2.setFirstName("Kitty");
        c2.setLastName("Y");
        OrderedProduct p2 = new OrderedProduct("POP EYES","french fries","3.5",1);
        OrderedProduct p3 = new OrderedProduct("POP EYES","coke","2",5);
        HashSet<OrderedProduct> list2 = new HashSet<>();
        list2.add(p2);
        list2.add(p3);
        Order o2 = new Order(sto2,c2,list2);
        orderList.add(o2);

        StoreOwner sto3 = new StoreOwner();
        sto3.setStoreName("Walmart");
        Customer c3 = new Customer();
        c3.setFirstName("Jerry");
        c3.setLastName("D");
        OrderedProduct p4 = new OrderedProduct("","tomato","3.5",2);
        OrderedProduct p5 = new OrderedProduct("","pork","2",1);
        HashSet<OrderedProduct> list3 = new HashSet<>();
        list3.add(p4);
        list3.add(p5);
        Order o3 = new Order(sto3,c3,list3);
        orderList.add(o3);

        StoreOwner sto4 = new StoreOwner();
        sto4.setStoreName("Walmart");
        Customer c4 = new Customer();
        c4.setFirstName("David");
        c4.setLastName("K");
        OrderedProduct p6 = new OrderedProduct("","milk","3.5",8);
        OrderedProduct p7 = new OrderedProduct("","bread","2",2);
        HashSet<OrderedProduct> list4 = new HashSet<>();
        list4.add(p6);
        list4.add(p7);
        Order o4 = new Order(sto4,c4,list4);
        orderList.add(o4);
    }

    @Override
    public String toString(){
        String display = "";
        for(Order order:orderList){
            display = (display + "ORDER " + String.valueOf(orderList.indexOf(order) + 1) + "\n"
                    + order.toString() + "\n\n");
        }
        return display;
    }
}