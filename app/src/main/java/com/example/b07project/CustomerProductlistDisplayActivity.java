package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerProductlistDisplayActivity extends AppCompatActivity {
    private StoreOwner store;
    private String storeName;
    private User currentUser;
    private ListView listItems;
    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_productlist_display);

        storeName = getIntent().getStringExtra("storeName");
        currentUser = (User) getIntent().getSerializableExtra("currentUser");


        model = Model.getInstance();
        listItems = (ListView) findViewById(R.id.ListProduct);
        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                avocado(store.getProductList().get(i));
                getStore();
            }
        });
    }
    private void avocado(Product item) {
        Intent intent = new Intent(this, .class);
        intent.putExtra("item", (Parcelable) item);
        intent.putExtra("storeName", storeName);
        intent.putExtra("currentUser", (Parcelable) currentUser);
        //intent.putExtra("order", order);
        startActivity(intent);

    }
    private void getStore(){
        FirebaseDatabase.getInstance().getReference("Stores")
                .child(storeName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                store = snapshot.getValue(StoreOwner.class);
                ItemListAdapter adapter = new ItemListAdapter(CustomerProductlistDisplayActivity.this,
                        R.layout.item_list_item, store.getProductList());
                listItems.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}

