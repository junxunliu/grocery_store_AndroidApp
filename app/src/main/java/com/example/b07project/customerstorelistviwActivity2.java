package com.example.b07project;

import static androidx.core.content.ContextCompat.startActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class customerstorelistviwActivity2 extends AppCompatActivity implements View.OnClickListener{

    private ListView listStores;

    private List<String> storeNames;
    private String currentUserID;
    private User currentUser;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerstorelistviw2);


        currentUserID = getIntent().getStringExtra("currentUserID");

        getCurrentUser();
        getStoreNames();

        listStores = (ListView) findViewById(R.id.listStores);
        listStores.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String storeName = storeNames.get(i);
                Intent intent = new Intent(customerstorelistviwActivity2.this, DisplayStoreActivity.class);
                intent.putExtra("storeName", storeName);
                intent.putExtra("currentUser", (Parcelable) currentUser);
                startActivity(intent);
            }
        });

    }


    private void getCurrentUser() {
        FirebaseDatabase.getInstance().getReference("XXX")
                .child("key").setValue(currentUser)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        callback.accept(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
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