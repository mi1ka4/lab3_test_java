
package com.example.lab3_test.shop;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, Product> catalog;
    private final Map<String, Integer> items = new HashMap<>();
    private int discountPercent = 0;

    public ShoppingCart(Map<String, Product> catalog) {
        this.catalog = catalog;
    }

    public Product getProduct(String id){
        return null;
    }

    public void ApplyPromo(String code){
        
    }
    
    public void AddProduct(String id, int quantity){

    }

    public void RemoveProduct(String id, int quantity) {

    }

    public void RemoveProduct(String id) {

    }

    public BigDecimal TotalPrice(){
        return null;
    }

    public Map<String, Integer>  getItems(){
        return new HashMap<>(this.items);
    }




    public static class ShoppingCartException extends RuntimeException {
        public ShoppingCartException(String message) { super(message); }
        public ShoppingCartException(String message, Throwable cause) { super(message, cause); }
    }

    public static class ProductNotFoundException extends ShoppingCartException {
        public ProductNotFoundException(String message) { super(message); }
    }

    public static class InvalidQuantityException extends ShoppingCartException {
        public InvalidQuantityException(String message) { super(message); }
    }

    public static class InsufficientStockException extends ShoppingCartException {
        public InsufficientStockException(String message) { super(message); }
    }

    public static class InvalidPromoCodeException extends ShoppingCartException {
        public InvalidPromoCodeException(String message) { super(message); }
    }
}