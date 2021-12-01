package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        init();
        //Test();
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
    }

    public void Test(){
        /*
        StoreOwner sto = new StoreOwner();
        sto.setStoreName("KFC");
        Customer c = new Customer();
        c.setFirstName("Kitty");
        c.setLastName("Y");
        OrderedProduct p = new OrderedProduct("KFC","chicken","9.99",4);
        HashSet<OrderedProduct> list = new HashSet<>();
        list.add(p);
        Order o = new Order(sto,c,list);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("OrderList").child("1").setValue(o);*/
    }

    private void display(){
        OrderList orderlist = new OrderList();
        orderlist.testData();
        User user = new User();
        user.setFirstName("David");
        user.setLastName("K");
        user.setUserType("Customer");
        OrderList CustomerOrderList = new OrderList();
        CustomerOrderList = orderlist.search(user);

        tv_display.setText(CustomerOrderList.toString());

        /*OrderList orderlist = new OrderList();
        //orderlist.readFromDB();
        OrderList customerOrderList = new OrderList();
        //User implements Serializable
        //data from customer main page: getIntent().getSerializableExtra();
        //Person person =(Person) getIntent().getSerializableExtra("person_data");
        //customerOrderList = orderlist.search(user);
        tv_display.setText(customerOrderList.toString());*/
    }
}