package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.b07project.StoreOwner;

public class StoreOwnerMainPageActivity extends AppCompatActivity {

    private String thisUserID;

    private StoreOwner store;

    private ListView ProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main_page);

        thisUserID = getIntent().getStringExtra("thisUserID");

        private void findStoreOnwer(){

            model.getStoreByOwner(thisUserID, (Store store) -> {
                if (store == null) {
                    Intent intent = new Intent(this, CreateStoreActivity.class);
                    intent.putExtra("currentUserID", thisUserID);
                    startActivity(intent);
                    return;
                }
                this.store = store;
                ItemListAdapter adapter = new ItemListAdapter(this, R.layout.item_list_item, store.product);
                ProductList.setAdapter(adapter);

            });
        }

        findStoreOnwer();


        findViewById(R.id.btnListOrders).setOnClickListener(this);
        findViewById(R.id.btnAddItem).setOnClickListener(this);
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
}