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

    private String StoreName;
    private String CustomerName;
    private String CustomerId;
    private List<OrderedProduct> order = new ArrayList<OrderedProduct>();
    private String status;

    public void addOrderedProduct(OrderedProduct p){
        order.add(p);
    }

    public Order(String storeName, String customerName, String customerId, List<OrderedProduct> order) {
        StoreName = storeName;
        CustomerName = customerName;
        CustomerId = customerId;
        this.order = order;
        status = "Incomplete";
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
        return order;
    }

    public String getStatus() {
        return status;
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

    public void setOrder(List<OrderedProduct> order) {
        this.order = order;
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

    /*
    public void sendOrder(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("Orders");
        Product p = new Product("chicken","kfc","2.99");
        usersRef.child("test").setValue(p).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerOrderListActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CustomerOrderListActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    @Override
    public String toString(){
        String display = "";
        display = ("Store Info: " + StoreName + "\n"
                + "Customer Info: " + CustomerName + "\n"
                + "Current Status: " + status + "\n");
        for(OrderedProduct p:order){
            display = (display + "   - " + p.name + " $" + p.price + " * " + p.quantity + "\n");
        }
        display = display + "\n";
        return display;
    }
}