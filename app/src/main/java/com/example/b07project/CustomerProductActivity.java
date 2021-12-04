package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomerProductActivity extends AppCompatActivity {

    private Button button;
    private TextView textViewQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product);
        button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        Product p = (Product) getIntent().getSerializableExtra("product");
        Order order = (Order) getIntent().getSerializableExtra("order");
        textView.setText(p.toString());
        textViewQuantity = (TextView) findViewById(R.id.editTextNumber);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(textViewQuantity.getText().toString().trim());
                OrderedProduct orderedProduct = new OrderedProduct(p.getBrand(),p.getName(),p.getPrice(),quantity);
                Log.i("product demo", orderedProduct.toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultOrderedProduct",orderedProduct);
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });


    }
}