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

    private Button btn_product;
    private Button btn_order;
    private ListView lv_display;

    StoreOrderAdapter adpt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_order_list);

        init();
        displayListView();
        //checkClick();
        //displayListView();

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
        btn_product = (Button) findViewById(R.id.button4);
        btn_order = (Button) findViewById(R.id.button5);
        lv_display = (ListView) findViewById(R.id.listView);
    }

    private void displayListView(){
        OrderList orderlist = new OrderList();
        orderlist.testData();

        //test data
        User user2 = new User();
        StoreOwner st = new StoreOwner();
        st.setStoreName("Walmart");
        user2 = (User)st;
        user2.setUserType("Store Owner");
        OrderList StoreOrderList = new OrderList();
        StoreOrderList = orderlist.search(user2);
        ArrayList<Order> list = new ArrayList<>();
        for(Order o:StoreOrderList.getList()){
            list.add(o);
        }

        adpt = new StoreOrderAdapter(this,R.layout.layout_store_order,list);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adpt);
    }

    /*private void checkClick() {
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = (Order) cb.getTag();
                order.setStatus(cb.isChecked());
            }
        });
    }*/
}