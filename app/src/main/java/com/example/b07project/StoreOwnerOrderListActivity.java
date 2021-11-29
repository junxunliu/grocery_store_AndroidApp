package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class StoreOwnerOrderListActivity extends AppCompatActivity {

    StoreOrderAdapter adpt = null;


    private void init(){

    }

    private void displayListView(){
        ArrayList<StoreOrder> stOrderList = new ArrayList<StoreOrder>();
        OrderList orderlist = new OrderList();
        orderlist.readFromDB();
        OrderList storeOrderList = new OrderList();
        //storeOrderList = orderlist.search(user);

        //for(Order o :){
            //StoreOrder order = new StoreOrder(o.toString(),false);

        //}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_order_list);

        init();
    }
}