package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CustomerStoreListViewActivity extends AddProductActivity  {
    private ListView listStores;

    private List<String> storeNames;
    private String currentUserID;
    private User currentUser;
    private DatabaseReference usersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_store_list);

        currentUserID = getIntent().getStringExtra("currentUserID");

        getCurrentUser();
        getStoreNames();

        listStores = (ListView) findViewById(R.id.listStores);
        listStores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String storeName = storeNames.get(i);
                Intent intent = new Intent(CustomerStoreListViewActivity.this, DisplayStoreActivity.class);
                intent.putExtra("storeName", storeName);
                intent.putExtra("currentUser", (Parcelable) currentUser);
                startActivity(intent);
            }
        });
    }

    private void getCurrentUser() {
        Model.getUser(currentUserID, (User user) -> { this.currentUser = user; });
    }



    private void getStoreNames() {

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_order:
                startActivity(new Intent(this, CustomerOrderListActivity.class));
                break;
        }
    }
}
