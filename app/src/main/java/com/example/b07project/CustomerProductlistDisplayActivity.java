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
                avocado(store.getProductList().get(i)); //zion: maybe getstore first and avacado next?
                getStore();
            }
        });
    }
    private void avocado(Product item) {
        Intent intent = new Intent(this, CustomerProductActivity.class);
        intent.putExtra("item", (Parcelable) item);
        intent.putExtra("storeName", storeName);
        intent.putExtra("currentUser", (Parcelable) currentUser);
        //intent.putExtra("order", order);
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
        FirebaseDatabase.getInstance().getReference("Stores")
                .child(storeName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                store = snapshot.getValue(StoreOwner.class);
                ProductListAdapter adapter = new ProductListAdapter((ValueEventListener) CustomerProductlistDisplayActivity.this,
                        R.layout.product_list, (List<Product>) store.getProductList());
                listItems.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    // after return, this function is called
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            //do something here after detecting a return from customerProductActivity
        }
    }

}



