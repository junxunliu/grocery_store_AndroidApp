package com.example.b07project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


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
    private String currentUserID;
    private List<String> storeNames;
    private User currentUser;
    private StoreOwner store;
    private DatabaseReference ref;
    private Button btnViewOrder;
    private Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_store_list);
        //currentUserID = getIntent().getStringExtra("currentUserID");

        //getCurrentUser();
        getStoreNames((List<String> storeNames) -> {
            this.storeNames = storeNames;
            ArrayAdapter<String> storeAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, storeNames);
            listStores.setAdapter(storeAdapter);
        });

         btnViewOrder = (Button) findViewById(R.id.button_view_order);
         btnViewOrder.setOnClickListener(this);
        
         listStores = (ListView) findViewById(R.id.listStores);
         listStores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String storeName = storeNames.get(i);
                Intent intent = new Intent(CustomerStoreListViewActivity.this,
                        DisplayStoreProductActivity.class);
                intent.putExtra("storeName", storeName);
                intent.putExtra("currentUser", (Parcelable) currentUser);
                startActivity(intent);
            }
        });
    }

    private void getCurrentUser() {
        model.gUser(currentUserID, (User user) -> { this.currentUser = user; });
    }



    private void getStoreNames(Consumer<List<String>> callback) {
        ref = FirebaseDatabase.getInstance().getReference("store");
        ref.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> storeNames = new ArrayList<String>();
                        for (DataSnapshot userSnapShot: snapshot.getChildren()) {
                            StoreOwner store = userSnapShot.getValue(StoreOwner.class);
                            storeNames.add(store.getStoreName());
                        }
                        callback.accept(storeNames);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_view_order:
                startActivity(new Intent(this, CustomerOrderListActivity.class));
                break;
        }
    }
}
