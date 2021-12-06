package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.b07project.StoreOwner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StoreOwnerMainPageActivity extends AppCompatActivity implements View.OnClickListener{

    private StoreOwner store = null;
    private TextView textViewSignOut;
    private ListView ProductList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main_page);

        //store = (StoreOwner) getIntent().getSerializableExtra("Store Owner");

        findStore();

        findViewById(R.id.btListOfOrders).setOnClickListener(this);
        findViewById(R.id.btAddProduct).setOnClickListener(this);
        ProductList = findViewById(R.id.lvItems);
        textViewSignOut = (TextView) findViewById(R.id.signout);
        textViewSignOut.setOnClickListener(this);
        //could be a new page having the delete button
//        ProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

//            @Override
//           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(StoreOwnerMainPageActivity.this, AddProductActivity.class);
//                intent.putExtra("store", store);
//                startActivity(intent);
//           }
//       });
    }

    private void findStore(){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //find the current store in the database
        FirebaseDatabase.getInstance().getReference("Users/Store Owners")
                .child(userId).addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StoreOwner s = snapshot.getValue(StoreOwner.class);
                if(s != null){

                    store = s;
                    ProductListAdapter adapter = new ProductListAdapter(StoreOwnerMainPageActivity.this, R.layout.product_list, (List<Product>)store.getProductList());
                    ProductList.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }

    private void addProduct() {
        Intent intent = new Intent(this, AddProductActivity.class);
        intent.putExtra("store", store);
        startActivity(intent);
    }


    private void listOfOrders() {
        Intent intent = new Intent(this, StoreOwnerOrderListActivity.class);
        intent.putExtra("store", store);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAddProduct:
                addProduct();
                break;
            case R.id.btListOfOrders:
                listOfOrders();
                break;
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LogInActivity.class));
                finish();
        }
    }



}