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

import java.util.List;

public class CustomerProductlistDisplayActivity extends AppCompatActivity {
    private StoreOwner store;
    private String storeName;
    private User currentUser;
    private ListView listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_productlist_display);

        storeName = getIntent().getStringExtra("storeName");
        currentUser = (User) getIntent().getSerializableExtra("currentUser");



        listItems = findViewById(R.id.ListProduct);
        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                avocado(store.getProductList().get(i));
                getStore();
            }
        });
    }
    private void avocado(Product item) {
        Intent intent = new Intent(this, CustomerProductActivity.class);
        intent.putExtra("item", item);
        intent.putExtra("storeName", storeName);
        intent.putExtra("currentUser", currentUser);
        //intent.putExtra("order", order);
        startActivity(intent);
    }

    private void getStore(){
        FirebaseDatabase.getInstance().getReference("Users/Store Owners")
                .orderByChild("storeName").equalTo(storeName)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sp : snapshot.getChildren()) {
                            store = sp.getValue(StoreOwner.class);
                            ProductListAdapter adapter = new ProductListAdapter(this, R.layout.product_list, store.getProductList());
                            listItems.setAdapter(adapter);
                            return;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}

