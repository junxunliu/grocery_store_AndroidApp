package com.example.b07project;

public class StoreOrder {

    private Order order;
    private boolean check;

    public StoreOrder(){
    }

    public StoreOrder(Order order, boolean check){
        this.order = order;
        this.check = check;
    }

    public Order getOrder(){
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isChecked(){
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString(){
        String display = "";
        display = ("Store Info: " + order.getStore().getStoreName() + "\n"
                + "Customer Info: " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName() + "\n"
                + "Ordered Product: " + "\n");
        for(OrderedProduct p:order.getOrder()){
            display = (display + "  " + "product: " + p.name + "    "
                    + "quantity: " + p.quantity + " " + "\n");
        }
        String s = "";
        if(check == false) s = "incomplete";
        else s = "complete";
        display = display + "Current Status: " + s + "\n";
        return display;
    }
}