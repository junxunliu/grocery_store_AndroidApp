package com.example.b07project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CustomerStoreListViewActivity extends AddProductActivity  {
    private ListView listStores;
    private List<String> storeNames;
    private User currentUser;
    private Button btnViewOrder;
    private TextView textViewSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_store_list);
        currentUser = (User) getIntent().getSerializableExtra("Customer");

        textViewSignOut = (TextView) findViewById(R.id.customerSignOut);
        textViewSignOut.setOnClickListener(this);


        getStoreNames();

         btnViewOrder = (Button) findViewById(R.id.button_view_order);
         btnViewOrder.setOnClickListener(this);
        
         listStores = (ListView) findViewById(R.id.listStores);
         listStores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String storeName = storeNames.get(i);
                Intent intent = new Intent(CustomerStoreListViewActivity.this,
                        CustomerProductlistDisplayActivity.class);
                intent.putExtra("storeName", storeName);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });
    }


    private void getStoreNames() {
        FirebaseDatabase.getInstance().getReference("Users/Store Owners")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        storeNames = new ArrayList<String>();
                        for (DataSnapshot userSnapShot: snapshot.getChildren()) {
                            StoreOwner store = userSnapShot.getValue(StoreOwner.class);
                            storeNames.add(store.getStoreName());
                        }
                       ArrayAdapter<String> storeAdapter = new ArrayAdapter<String>(
                               CustomerStoreListViewActivity.this
                               , android.R.layout.
                                simple_list_item_1, storeNames);
                        listStores.setAdapter(storeAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_view_order:
                Intent intent = new Intent(this, CustomerOrderListActivity.class);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
                break;
            case R.id.customerSignOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LogInActivity.class));
                finish();
        }
    }
}
