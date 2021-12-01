package com.example.b07project;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreOwner extends User implements Serializable {

    //private ProductList productList = new ProductList(this);
    private List<Product> productList = new ArrayList<>();

    public StoreOwner(String email, String firstName, String lastName, String storeName, String storeAddress){
        super(email, firstName, lastName, storeName, storeAddress);
    }

    public StoreOwner(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }

    public StoreOwner(String storeName, String address){
        storeName = getStoreName();
        address = getStoreAddress();
    }

    public StoreOwner() {

    }

    //public ProductList getProductList()

    public void addProduct(Product p) {
        productList.add(p);
    }
    public List<Product> getProductList() {return productList;}

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
