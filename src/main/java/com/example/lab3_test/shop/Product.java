package com.example.lab3_test.shop;


public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Product(String product_id, String name, double price, int stock){
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
        return false;
    }

    @Override
    public String toString() {
        return "";
        // return "Product{id='%s', name='%s', price=%s, stock=%d}"
        //         .formatted(id, name, price, stock);
    }
}
