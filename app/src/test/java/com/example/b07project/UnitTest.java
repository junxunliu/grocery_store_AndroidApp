package com.example.b07project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void Product_test(){
        Product product = new Product("Nestle","Water",0.99);
        System.out.println(product);
    }

    @Test
    public void OrderedProduct_test(){
        OrderedProduct orderedProduct = new OrderedProduct("Nestle","Water",0.99,2);
        System.out.println(orderedProduct);
    }

    @Test
    public void ProductList_test(){
        Product p1 = new Product("Nestle","water",0.99);
        Product p2 = new Product("Alokozay","tissue",2.99);
        Product p3 = new Product("Nintendo","switch console",399);
        Product p4 = new Product("Casio","calculator",30);
        ProductList productList = new ProductList();
        productList.addProduct(p1);
        productList.addProduct(p2);
        productList.addProduct(p3);
        productList.addProduct(p4);
        System.out.println(productList);
    }
}