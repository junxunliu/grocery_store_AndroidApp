package com.example.b07project;

public class Order {
    String sid;
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
    }
/*    public static void main(String[] arg){
        System.out.println("hh");
        Order order = new Order("s01:","p01:",3);
        System.out.println(order.toString());
    }*/

}


