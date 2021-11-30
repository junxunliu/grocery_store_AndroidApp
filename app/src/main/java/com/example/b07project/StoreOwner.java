package com.example.b07project;


import java.io.Serializable;
import java.util.Objects;

public class StoreOwner extends User implements Serializable {
    private String storeName;
    private String address;
    private ProductList productList;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ProductList getProductList() {
        return productList;
    }

    public StoreOwner(){productList = new ProductList(this);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreOwner)) return false;
        StoreOwner that = (StoreOwner) o;
        return getStoreName().equals(that.getStoreName()) && getAddress().equals(that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoreName(), getAddress());
    }

    public StoreOwner(String storeName, String address){
        this.storeName=storeName;
        this.address=address;
    }

    @Override
    public String toString() {
        return address + ":" + storeName;

    }
}
