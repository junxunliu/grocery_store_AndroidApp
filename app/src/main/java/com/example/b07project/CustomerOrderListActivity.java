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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.List;

public class CustomerOrderListActivity extends AppCompatActivity {

    private TextView tv_display;
    private TextView tv_title;
    private Button btn_storeList;

    private String userId;
    private User user;
    private OrderList orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        init();
        displayOrders();
        //display();

        btn_storeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //initialize all the fields
    private void init(){
        tv_display = (TextView) findViewById(R.id.textView2);
        tv_title = (TextView) findViewById(R.id.my_order);
        btn_storeList = (Button) findViewById(R.id.button2);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        user = (User) getIntent().getSerializableExtra("currentUser");
        user.setUserId(userId);
        orderList = new OrderList();
    }

    public void displayOrders(){
        //read orders from database and store them in the orderList
        FirebaseDatabase.getInstance().getReference("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child:snapshot.getChildren()) {
                    Order order;
                    order = child.getValue(Order.class);
                    //Log.i("b07info", order.toString());
                    orderList.addOrder(order);
                    //Log.i("orderList", orderList.toString());
                }
                //search in the orderList, find orders for the current customer and add them into CustomerOrderList
                OrderList CustomerOrderList;
                CustomerOrderList = orderList.search(user);
                //display orders in the TextView
                if(CustomerOrderList.getList().size() == 0){
                    Toast.makeText(CustomerOrderListActivity.this, "There is NO order !",Toast.LENGTH_SHORT).show();
                }
                else{
                    tv_display.setText(CustomerOrderList.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    /*
    private void display(){
        OrderList CustomerOrderList = new OrderList();
        CustomerOrderList = orderList.search(user);
        tv_display.setText(CustomerOrderList.toString());
    }*/
}