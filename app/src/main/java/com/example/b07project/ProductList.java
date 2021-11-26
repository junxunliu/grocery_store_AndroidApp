package com.example.b07project;


import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> productList= new ArrayList<>();
    private StoreOwner storeOwner;

    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }


    public void addProduct(Product p){
        productList.add(p);
    }

    public void deleteProduct(Product p){
        productList.remove(p);
    }

    @Override
    public String toString() {
        String str="";
        for(Product p:productList){
            str += p.toString() + "\n";
        }
        return str;
    }
}
