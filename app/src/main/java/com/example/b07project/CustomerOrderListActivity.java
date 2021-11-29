package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerOrderListActivity extends AppCompatActivity {

    private TextView tv_display;
    private Button btn_storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        init();
        display();

        btn_storeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(CustomerOrderListActivity.this, target_activity.class);
                //startActivity(intent);
            }
        });
    }

    private void init(){
        tv_display.findViewById(R.id.textView2);
        btn_storeList.findViewById(R.id.button2);
    }

    private void display(){
        OrderList orderlist = new OrderList();
        orderlist.readFromDB();
        OrderList customerOrderList = new OrderList();
        //User implements Serializable
        //data from customer main page: getIntent().getSerializableExtra();
        //Person person =(Person) getIntent().getSerializableExtra("person_data");
        //customerOrderList = orderlist.search(user);
        tv_display.setText(customerOrderList.toString());
    }
}