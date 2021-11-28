package com.example.b07project;

import java.util.HashSet;

public class Order {
    StoreOwner s;
    Customer c;
    HashSet<OrderedProduct> order = new HashSet<OrderedProduct>();
    boolean status;

    public Order(){ }

    public Order(StoreOwner s, Customer c, HashSet<OrderedProduct> order, boolean status){
        this.s = s;
        this.c = c;
        this.order = order;
        this.status = false;
    }

    public void setStatus(){

    }

    @Override
    public String toString(){
        String display = "";
        display = ("Store Info: " + s.storeName + "\n"
                + "Customer Info: " + c.firstName + " " + c.lastName + "\n"
                + "Ordered Product: " + "\n");
        for(OrderedProduct p:order){
            display = display + "  " + p.toString() + "\n";
        }
        String s = "";
        if(status == false) s = "incomplete";
        else s = "complete";
        display = display + "Current Status: " + s + "\n";
        return display;
    }

    /*String sid;
    String pid;
    String num;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString(){
        return sid+":"+pid+":"+num;
    }

    public Order(){

    }

    public Order(String sid, String pid, String num){
        this.sid=sid;
        this.pid=pid;
        this.num=num;
    }*/
/*    public static void main(String[] arg){
        System.out.println("hh");
        Order order = new Order("s01:","p01:",3);
        System.out.println(order.toString());
    }*/

}


