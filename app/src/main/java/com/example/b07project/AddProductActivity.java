package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edTxtProductName, edTxtBrandName, edTxtPrice;
    private Button btAdd;

    private StoreOwner store;
    private String thisUserID;

    Pattern patternPrice = Pattern.compile("[0-9]*\\.?[0-9]+");
    //Pattern patternPrice = Pattern.compile("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        store = (StoreOwner) getIntent().getSerializableExtra("store");

        // thisUserID = getIntent().getStringExtra("thisUsrID");

        edTxtProductName = findViewById(R.id.edTxtProductName);
        edTxtBrandName = findViewById(R.id.edTxtBrandName);
        edTxtPrice = findViewById(R.id.edTxtPrice);
        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);

    }

    private void add() {

        String productName = edTxtProductName.getText().toString().trim();
        String brandName = edTxtBrandName.getText().toString().trim();
        String price = edTxtPrice.getText().toString().trim();

        Product NewProduct = new Product(productName, brandName, price);

        if (productName.isEmpty()) {
            edTxtProductName.setError("Product name is required!");
            edTxtProductName.requestFocus();
            return;
        }

        if (brandName.isEmpty()) {
            edTxtBrandName.setError("Brand name is required!");
            edTxtBrandName.requestFocus();
            return;
        }

        if (price.isEmpty()) {
            edTxtPrice.setError("Price is required!");
            edTxtPrice.requestFocus();
            return;
        }

        if (!patternPrice.matcher(price).matches()) {
            edTxtPrice.setError("Invalid price!");
            edTxtPrice.requestFocus();
            return;
        }



        //add the product to the product list
        store.addProduct(NewProduct);

        //find the current user
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference("Users/Store Owners")
                .child(userId).setValue(store).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Do Something
                            Toast.makeText(AddProductActivity.this, "Product Added Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductActivity.this, StoreOwnerMainPageActivity.class);
                            intent.putExtra("thisUsrID", store.getFirstName());
                            startActivity(intent);
                        } else {
                            // Do Something
                            Toast.makeText(AddProductActivity.this, "Product Not Added!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAdd:
                add();
                break;
        }
    }

}