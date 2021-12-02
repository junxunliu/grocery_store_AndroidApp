package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreOwnerOrderListActivity extends AppCompatActivity {

    private TextView tv_title_orders;
    private Button btn_product;
    private Button btn_order;
    private ListView lv_display;

    private User user;
    private OrderList orderList;

    StoreOrderAdapter adpt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_order_list);

        init();
        updateOrders();
        displayListView();

        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //StoreOwnerMainPage CANNOT run
                Intent intent = new Intent(StoreOwnerOrderListActivity.this, StoreOwnerMainPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        tv_title_orders = (TextView) findViewById(R.id.stoList);
        btn_product = (Button) findViewById(R.id.button4);
        btn_order = (Button) findViewById(R.id.button5);
        lv_display = (ListView) findViewById(R.id.listView);
        user = (User) getIntent().getSerializableExtra("currentUserID");
    }

    private void updateOrders(){

    }

    private void displayListView(){
        OrderList StoreOrderList = new OrderList();
        StoreOrderList = orderList.search(user);

        ArrayList<Order> list = new ArrayList<>();
        for(Order o:StoreOrderList.getList()){
            list.add(o);
        }

        adpt = new StoreOrderAdapter(this,R.layout.layout_store_order,list);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adpt);
    }
}