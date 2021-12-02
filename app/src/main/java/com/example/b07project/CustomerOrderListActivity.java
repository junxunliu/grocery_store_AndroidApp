package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;

public class CustomerOrderListActivity extends AppCompatActivity {

    private TextView tv_display;
    private TextView tv_title;
    private Button btn_storeList;
    private Button btn_myOrder;

    private User user;
    private OrderList orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        init();
        //read();
        display();

        btn_storeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerOrderListActivity.this, CustomerStoreListViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        tv_display = (TextView) findViewById(R.id.textView2);
        tv_title = (TextView) findViewById(R.id.my_order);
        btn_storeList = (Button) findViewById(R.id.button2);
        btn_myOrder = (Button) findViewById(R.id.button3);
        user = (User) getIntent().getSerializableExtra("currentUser");
        orderList = new OrderList();
    }
/*
    public void read(){
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
    }

 */

    private void display(){
        OrderList CustomerOrderList = new OrderList();
        CustomerOrderList = orderList.search(user);
        tv_display.setText(CustomerOrderList.toString());
    }
}