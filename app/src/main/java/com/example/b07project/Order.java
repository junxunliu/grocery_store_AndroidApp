package com.example.b07project;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Order implements Serializable {


    public String orderId;
    private String StoreName;
    private String CustomerName;
    private String CustomerId;
    private List<OrderedProduct> orderedProductList = new ArrayList<OrderedProduct>();
    private String status;

    public void addOrderedProduct(OrderedProduct p){
        orderedProductList.add(p);

    }

    public Order() {

    }

    public Order(String storeName, String customerName, String customerId) {
        this();
        StoreName = storeName;
        CustomerName = customerName;
        CustomerId = customerId;
        status = "Incomplete";
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStoreName() {
        return StoreName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public List<OrderedProduct> getOrder() {
        return orderedProductList;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public void setOrder(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean convertStatus(){
        if(status.equals("Complete")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String display = "";
        display = ("Store Info: " + StoreName + "\n"
                + "Customer Info: " + CustomerName + "\n"
                + "Current Status: " + status + "\n"
                + "Ordered Product: " + "\n");
        for(OrderedProduct p:orderedProductList){
            display = (display + "   - " + p.name + " $" + p.price + " * " + p.quantity + "\n");
        }
        display = display + "\n";
        return display;
    }

    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if (!(o instanceof Order)) return false;
        Order order = (Order)o;
        return orderId.equals(order.orderId) && StoreName.equals(order.StoreName)
                && CustomerName.equals(order.CustomerName) && CustomerId.equals(order.CustomerId)
                && status.equals(order.status) && orderedProductList.equals(order.orderedProductList) ;
    }
}