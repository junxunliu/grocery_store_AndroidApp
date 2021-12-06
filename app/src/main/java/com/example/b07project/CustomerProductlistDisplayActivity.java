package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CustomerProductlistDisplayActivity extends AppCompatActivity implements View.OnClickListener {
    private StoreOwner store;
    private String storeName;
    private User currentUser;
    private Order order;
    private ListView listItems;
    private List<OrderedProduct> OrderedProductList = new ArrayList<OrderedProduct>();
    private Button place_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_productlist_display);

        storeName = getIntent().getStringExtra("storeName");
        currentUser = (User) getIntent().getSerializableExtra("currentUser");
//        Log.i("demo debug", currentUser.getFirstName());
        String customerName = currentUser.getFirstName() + " " + currentUser.getLastName();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        order = new Order();
        order.setStoreName(storeName);
        order.setStatus("Incomplete");
        order.setCustomerName(customerName);
        order.setCustomerId(userId);
        place_order = findViewById(R.id.place_order_button);
        place_order.setOnClickListener(this);

        listItems = findViewById(R.id.ListProduct);
        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setActivity(store.getProductList().get(i));

            }
        });
        getStore();
    }
    private void setActivity(Product item) {
        Intent intent = new Intent(this, CustomerProductActivity.class);
        intent.putExtra("product", item);
        intent.putExtra("storeName", storeName);
        intent.putExtra("currentUser", currentUser);
        intent.putExtra("order",  order);
        startActivityForResult(intent, 1);
        //z: pass product and order to me
        /*intent.putExtra("product", item);
        intent.putExtra("order", order);
        startActivityForResult(intent,1);*/

/*        pass 2 things to 5.3: product the user chooses and the current maintained order. In 5.3,
        a new orderedProduct is created and added into order. Since order is just a pointer, there is
        no need to pass anything back. The newly created orderProduct is passed back in case you need it.
        orderedProduct = (OrderedProduct) data.getSerializableExtra("resultOrderedProduct");
*/

    }

    private void getStore(){
        FirebaseDatabase.getInstance().getReference("Users/Store Owners")
                .orderByChild("storeName").equalTo(storeName)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sp : snapshot.getChildren()) {
                            store = sp.getValue(StoreOwner.class);
                            ProductListAdapter adapter = new ProductListAdapter(CustomerProductlistDisplayActivity.this, R.layout.
                                    product_list, store.getProductList());
                            listItems.setAdapter(adapter);
                            return;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
    private void sendOrder() {
            String key = FirebaseDatabase.getInstance().getReference("Order").push().getKey();
            order.setOrderId(key);
            FirebaseDatabase.getInstance().getReference("Order").
                    child(key).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (order.getOrder().size() == 0) {
                        Toast.makeText(CustomerProductlistDisplayActivity.this,
                                "failed to create Order.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(CustomerProductlistDisplayActivity.this,
                            "Order Created",
                            Toast.LENGTH_LONG).show();
                }
            });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.place_order_button:
                sendOrder();
                finish();
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OrderedProduct orderedProduct;

        if(resultCode == RESULT_OK)
        {
            orderedProduct = (OrderedProduct) data.getSerializableExtra("resultOrderedProduct");
            order.addOrderedProduct(orderedProduct);
            Log.i("product demo", order.toString());
            Toast.makeText(CustomerProductlistDisplayActivity.this, "add to cart", Toast.LENGTH_LONG).show();
        }
    }
}

