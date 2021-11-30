package com.example.b07project;


import java.io.Serializable;
import java.util.Objects;

public class StoreOwner extends User {

    ProductList productList = new ProductList(this);

    public StoreOwner(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }

    public StoreOwner(String storeName, String address){
        storeName = getStoreName();
        address = getStoreAddress();
    }

    public StoreOwner() {

    }

    public ProductList getProductList() {
        return productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreOwner)) return false;
        StoreOwner that = (StoreOwner) o;
        return getStoreName().equals(that.getStoreName()) && getStoreName().equals(that.getStoreAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getStoreAddress());
    }

    @Override
    public String toString() {
        return getStoreAddress() + ":" + getStoreName();

    }
}
