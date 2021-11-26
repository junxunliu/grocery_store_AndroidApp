package com.example.b07project;

import java.util.ArrayList;
import java.util.List;

public class StoreOwner {

    public String owner;
    public String storeName;

    public List<Product> product;

    public StoreOwner() {
        product = new ArrayList<Product>();

    }

    public StoreOwner(String storeName) {
        this();
        this.storeName = storeName;
    }

    public String toString() {
        return storeName;
    }
}
