
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
        Product product = this.catalog.get(id);
        if (product == null) throw new ProductNotFoundException("Товар не найден id="+id);
        return product;
    }

    public void ApplyPromo(String code){
        Map<String, Integer> promos = new HashMap<>();
        promos.put("SALE10", 10);
        promos.put("SALE20", 20);
        if (promos.get(code) == null) throw new InvalidPromoCodeException("Неизвестный промокод: "+code );
        this.discountPercent = promos.get(code);
        
    }
    
    public void AddProduct(String id, int quantity){
        if (quantity <= 0) throw new InvalidQuantityException("Количество должно быть положительным");
        

        Product product = getProduct(id);
        int current = items.getOrDefault(id, 0);
        int newQuantity = current + quantity;

        if (newQuantity > product.getStock()) {
            throw new InsufficientStockException("На складе только %d шт., запрошено %d шт.".formatted(product.getStock(), newQuantity));
        }

        items.put(id, newQuantity);
    }

    public void RemoveProduct(String id, Integer quantity) {
        if (!items.containsKey(id)) throw new ProductNotFoundException("Товара с id='" + id + "' нет в корзине");
        

        if (quantity == null) {
            items.remove(id);
            return;
        }
        if (quantity <= 0) throw new InvalidQuantityException("Количество должно быть положительным");
        
        int current = items.get(id);
        int newQuantity = current - quantity;
        if (newQuantity <= 0) items.remove(id);
        else items.put(id, newQuantity);
        
    }

    public void RemoveProduct(String id) {
        if (!items.containsKey(id)) 
            throw new ProductNotFoundException("Товара с id='" + id + "' нет в корзине");
    
        items.remove(id);
    }

    public double TotalPrice(){
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            Product product = catalog.get(entry.getKey());
            if (product != null) total += product.getPrice() * entry.getValue();
        }
        return Math.round(total * (1 - discountPercent / 100.0) * 100.0) / 100.0;
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