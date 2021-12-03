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

import java.util.HashSet;
import java.util.List;

public class CustomerProductlistDisplayActivity extends AppCompatActivity {
    private StoreOwner store;
    private String storeName;
    private User currentUser;
    private Order order;
    private ListView listItems;
    private HashSet<OrderedProduct> OrderedProductList = new HashSet<OrderedProduct>();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_productlist_display);

        storeName = getIntent().getStringExtra("storeName");
        currentUser = (User) getIntent().getSerializableExtra("currentUser");
        Customer customer = new Customer(currentUser.getEmail(),currentUser.getFirstName(),
                currentUser.getLastName());
        order = new Order(store, customer, OrderedProductList);



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
        startActivity(intent);
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
    //private void sendOrder() {

        // create a Post
        //model.postOrder(order, (Order order) -> {
           // if (order == null) {
             //   Toast.makeText(this, "failed to create Order.", Toast.LENGTH_LONG).show();
               // return;
            //}

            // create a Post Description
            //orderDescription = new OrderDescription(order.orderID, currentUser.name, order.createdDate);
            //model.postOrderDescription(orderDescription, (OrderDescription orderDesc) -> {

                // Get the Store
//              getStoreByName();

                    // Update the store
                //    store.pendingOrders.add(orderDesc);


                  //  model.postStore(store, (Boolean storePosted) -> {
                    //    Toast.makeText(this, "Order created.", Toast.LENGTH_LONG).show();
                    //});
                //});
            //});
        //});
    //}

//    public void getStoreByName() {
//       FirebaseDatabase.getInstance().getReference("Users/Store Owners")
//               .child(storeName).addListenerForSingleValueEvent(new ValueEventListener(){
//            @Override
//           public void onDataChange(@NonNull DataSnapshot snapshot) {
//                StoreOwner store = snapshot.getValue(StoreOwner.class);
//                callback.accept(store);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });
//

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {

        }
    }
}

