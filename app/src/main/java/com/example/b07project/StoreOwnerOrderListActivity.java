package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
        displayOrders();
        //displayListView();

        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init(){
        tv_title_orders = (TextView) findViewById(R.id.stoList);
        btn_product = (Button) findViewById(R.id.button4);
        btn_order = (Button) findViewById(R.id.button5);
        lv_display = (ListView) findViewById(R.id.listView);
        user = (User) getIntent().getSerializableExtra("store");
        //Log.i("b07info", user.getStoreName());
        orderList = new OrderList();
    }

    //read orders from database and store them in the orderList
    private void displayOrders(){
        //read orders from database and store them in the orderList
        FirebaseDatabase.getInstance().getReference("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child:snapshot.getChildren()) {
                    Order order;
                    order = child.getValue(Order.class);
                    //Log.i("b07info", order.toString());
                    //Log.i("IDIDIDID", order.getOrderId());
                    orderList.addOrder(order);
                    //Log.i("orderList", orderList.toString());
                }
                //search in the orderList, find orders for the current store owner and add them into StoreOrderList
                OrderList StoreOrderList;
                StoreOrderList = orderList.search(user);
                ArrayList<Order> list = new ArrayList<>();
                list = StoreOrderList.getList();
                //for(Order o:StoreOrderList.getList()){
                    //list.add(o);
                    //Log.i("list", list.toString());
                //}
                //display orders in the ListView
                adpt = new StoreOrderAdapter(StoreOwnerOrderListActivity.this,R.layout.layout_store_order,list);
                //ListView lv = (ListView) findViewById(R.id.listView);
                lv_display.setAdapter(adpt);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    /*
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
    }*/
}