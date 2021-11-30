package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.b07project.StoreOwner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreOwnerMainPageActivity extends AppCompatActivity implements View.OnClickListener{

    private String thisUser;

    private StoreOwner store = null;

    private ListView ProductList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main_page);

        store = (StoreOwner) getIntent().getSerializableExtra("Store Owner");

        findStore();

        findViewById(R.id.btListOfOrders).setOnClickListener(this);
        findViewById(R.id.btAddProduct).setOnClickListener(this);
        ProductList = findViewById(R.id.lvItems);
        ProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StoreOwnerMainPageActivity.this, AddProductActivity.class);
                intent.putExtra("store", store);
                startActivity(intent);
            }
        });
    }

    private void findStore(){

        //find the current store in the database
        FirebaseDatabase.getInstance().getReference("")
                .child("").addListenerForSingleValueEvent(new ValueEventListener(){

            //private StoreOwner store;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StoreOwner s = snapshot.getValue(StoreOwner.class);
                if(s != null){
                    //update the product list to the store
                    store = s;
                    ProductListAdapter adapter = new ProductListAdapter(this, R.layout.product_list, store.getProductList().getArrayList());
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
        // intent.putExtra("store", store);
        intent.putExtra("currentUserID", thisUser);
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
        }
    }



}