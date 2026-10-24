package com.example.lab3_test.shop;

import java.util.Objects;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Product(String product_id, String name, double price, int stock){

        if (price < 0) 
            throw new IllegalArgumentException("Price must be non-negative: " + price);
        
        if (stock < 0) 
            throw new IllegalArgumentException("Stock must be non-negative: " + stock);
        
        this.id = product_id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId(){ return id;}

    public String getName() {return name;}

    public double getPrice(){return price;}

    public int getStock(){return stock;}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return stock == other.stock
                && Double.compare(price, other.price) == 0
                && Objects.equals(id, other.id)
                && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Product{id='%s', name='%s', price=%s, stock=%d}"
                .formatted(id, name, price, stock);
    }
}
