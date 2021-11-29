package com.example.b07project;

public class StoreOrder {

    private String order;
    private boolean check;

    public StoreOrder(String order, boolean check){
        this.order = order;
        this.check = check;
    }

    public String getOrder(){
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isChecked(){
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}