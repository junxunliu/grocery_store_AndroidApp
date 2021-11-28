package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.b07project.Order;
import com.example.b07project.StoreOwner;
import com.example.b07project.User;


public class DisplayStoreActivity extends AppCompatActivity implements View.OnClickListener {

    private StoreOwner store;

    private String storeName;

    private User currentUser;

    private Button btnSendOrder;
    private ListView lvItems;

    private Order order;
    private OrderDescription orderDescription;

    private Model model;

    private Item selectedItem;
    private TextView tvSelectedItem, tvNumberOfItems;
    private EditText edTxtSelectedQuantity;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_store);

        storeName = getIntent().getStringExtra("storeName");
        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        order = new Order(storeName, currentUser.userID);
        model = Model.getInstance();



        tvSelectedItem = (TextView) findViewById(R.id.tvSelectedItem);
        tvNumberOfItems = (TextView) findViewById(R.id.tvNumberOfItems);
        edTxtSelectedQuantity = (EditText) findViewById(R.id.edTxtSelectedQuantity);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnSendOrder = (Button) findViewById(R.id.btnSendOrder);
        btnSendOrder.setOnClickListener(this);


        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = store.inventory.get(i);
                tvSelectedItem.setText(selectedItem.name);

            }
        });

        getStore();

    }

    private void getStore() {
        model.getStoreByName(storeName, (Store store) -> {
            this.store = store;

            ItemListAdapter adapter = new ItemListAdapter(this, R.layout.item_list_item, store.inventory);
            lvItems.setAdapter(adapter);

            selectedItem = store.inventory.get(0);
            tvSelectedItem.setText(selectedItem.name);
        });
    }


    private void addItem() {
        int quantity = Integer.parseInt(edTxtSelectedQuantity.getText().toString());

        order.addItem(new Item(selectedItem.name, selectedItem.price, quantity));
        Toast.makeText(this, selectedItem.name + " added.", Toast.LENGTH_SHORT).show();

        tvNumberOfItems.setText(((Integer) order.orderItems.size()).toString());
    }

    private void sendOrder() {

        // create a Post
        model.postOrder(order, (Order order) -> {
            if (order == null) {
                Toast.makeText(this, "failed to create Order.", Toast.LENGTH_LONG).show();
                return;
            }

            // create a Post Description
            orderDescription = new OrderDescription(order.orderID, currentUser.name, order.createdDate);
            model.postOrderDescription(orderDescription, (OrderDescription orderDesc) -> {

                // Get the Store
                model.getStoreByName(storeName, (Store store) -> {

                    // Update the store
                    store.pendingOrders.add(orderDesc);

                    for (Item orderItem : order.orderItems) {
                        Item item = store.inventory.get(store.inventory.indexOf(orderItem));
                        item.quantity -= orderItem.quantity;
                    }

                    model.postStore(store, (Boolean storePosted) -> {
                        Toast.makeText(this, "Order created.", Toast.LENGTH_LONG).show();
                    });
                });
            });
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendOrder:
                sendOrder();
                break;
            case R.id.btnAdd:
                addItem();
                break;
        }
    }
}
