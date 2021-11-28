package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StoreOwnerMainPageActivity extends AppCompatActivity {

    private String thisUserID;

    private StoreOwner store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_main_page);

        thisUserID = getIntent().getStringExtra("thisUserID");


    }
}