package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.b07project.StoreOwner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreOwnerMainPageActivity extends AppCompatActivity implements View.OnClickListener{

    private String thisUser;

    private StoreOwner store;

    private ListView ProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main_page);

        thisUser = getIntent().getStringExtra("currentUserID");

        private void findStore(){

            //find the current store in the database
            FirebaseDatabase.getInstance().getReference("user")
                    .child("email").addListenerForSingleValueEvent(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    StoreOwner store = snapshot.getValue(StoreOwner.class);
                    if(store != null){
                        //update the product list to the store
                        //this.store = store;
                        //ItemListAdapter adapter = new ItemListAdapter(this, R.layout.item_list_item, store.product);
                        //ProductList.setAdapter(adapter);
                    }else {
                        Intent intent = new Intent(this, CreateStoreActivity.class);
                        intent.putExtra("currentUserID", thisUser);
                        startActivity(intent);
                        return;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });

        }

        findViewById(R.id.btListOfOrders).setOnClickListener(this);
        findViewById(R.id.btAddProduct).setOnClickListener(this);
        ProductList = (ListView) findViewById(R.id.lvItems);
        ProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StoreOwnerMainPageActivity.this, AddProductActivity.class);
                intent.putExtra("store", store);
                startActivity(intent);
            }
        });

    }

    private void addItem() {
        Intent intent = new Intent(this, AddProductActivity.class);
        intent.putExtra("store", store);
        startActivity(intent);
    }


    private void listOrders() {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        // intent.putExtra("store", store);
        intent.putExtra("currentUserID", thisUser);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAddProduct:
                addItem();
                break;
            case R.id.btListOfOrders:
                listOrders();
                break;
        }
    }



}