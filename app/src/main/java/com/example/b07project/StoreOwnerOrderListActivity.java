package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //StoreOwnerMainPage CANNOT run
                Intent intent = new Intent(StoreOwnerOrderListActivity.this, StoreOwnerMainPageActivity.class);
                startActivity(intent);
            }
        });

        displayListView();
    }

    private void init(){
        btn_product = (Button) findViewById(R.id.button4);
        btn_order = (Button) findViewById(R.id.button5);
        lv_display = (ListView) findViewById(R.id.listView);
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

        adpt = new StoreOrderAdapter(this,R.layout.layout_store_order,stOrderList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adpt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StoreOrder order = (StoreOrder) parent.getItemAtPosition(position);
            }
        });
    }
}