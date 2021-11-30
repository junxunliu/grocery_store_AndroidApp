package com.example.b07project;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    String name;
    String brand;
    String price;


    public Product(){ }
    public Product(String name,String brand,String price){
        this.name=name;
        this.brand=brand;
        this.price=price;
    }

    @Override
    public String toString() {
        return brand + ":" + name + ":" + price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name) && brand.equals(product.brand) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
