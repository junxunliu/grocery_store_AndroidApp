package com.example.b07project;

import java.util.ArrayList;

public class OrderList {

    private ArrayList<Order> orderList = new ArrayList<Order>();

    public void addOrder(Order order){
        orderList.add(order);
    }

    public ArrayList<Order> search_by_store(){
        ArrayList<Order> storeOrderList = new ArrayList<Order>();

        return storeOrderList;
    }

    public ArrayList<Order> search_by_customer(){
        ArrayList<Order> CustomerOrderList = new ArrayList<Order>();

        return CustomerOrderList;
    }

    @Override
    public String toString(){
        String display = "";
        int i = 1;
        for(Order order:orderList){
            display = (display + "ORDER " + i + ":" + "\n"
                    + order.toString() + "\n\n");
            i++;
        }
        return display;
    }
}
