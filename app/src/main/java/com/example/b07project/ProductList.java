package com.example.b07project;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductList {

    public ArrayList<Product> getArrayList() {
        return arrayList;
    }

    private ArrayList<Product> arrayList= new ArrayList<>();
    private StoreOwner storeOwner;

    public ProductList(){}

    public ProductList(StoreOwner storeOwner){
        this.storeOwner=storeOwner;
    }

    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    public void addProduct(Product p){
        arrayList.add(p);
    }

    public void deleteProduct(Product p){
        arrayList.remove(p);
    }

    public void submitToDB(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        for(Product p: arrayList){
            ref.child("ProductList").child(this.getStoreOwner().getId()).child(String.valueOf(arrayList.indexOf(p))).setValue(p);
        }
/*        Product[] arr = arrayList.toArray(new Product[arrayList.size()]);
        ref.child("ProductList").child(this.getStoreOwner().getAddress()+
                ":"+this.getStoreOwner().getStoreName()).setValue(arr);*/

    }

/*    public void readFromDB(){
to be implemented
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductList)) return false;
        ProductList that = (ProductList) o;
        return arrayList.equals(that.arrayList) && storeOwner.equals(that.storeOwner);
    }

    @Override
    public int hashCode() {
        return storeOwner.hashCode();
    }

    @Override
    public String toString() {
        return storeOwner.toString();
    }
}
