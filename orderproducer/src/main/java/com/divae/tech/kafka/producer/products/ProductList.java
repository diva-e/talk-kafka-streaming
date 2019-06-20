package com.divae.tech.kafka.producer.products;

import java.util.List;
import java.util.Map;

public class ProductList {

    private List<Map<String, String>> products;


    public List<Map<String, String>> getProducts() {
        return products;
    }

    public void setProducts(List<Map<String, String>> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                '}';
    }
}
