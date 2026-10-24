package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.lab3_test.shop.Product;
import com.example.lab3_test.shop.ShoppingCart;

import com.example.lab3_test.shop.ShoppingCart.InsufficientStockException;
import com.example.lab3_test.shop.ShoppingCart.ProductNotFoundException;
import com.example.lab3_test.shop.ShoppingCart.InvalidQuantityException;
import com.example.lab3_test.shop.ShoppingCart.ShoppingCartException;
import com.example.lab3_test.shop.ShoppingCart.InvalidPromoCodeException;

public class TestShoppingCart {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        Product phone1 = new Product("prod_1", "Смартфон", 1000.0, 5);
        Product phone2 = new Product("prod_2", "Наушники", 500.0, 10);
        Map<String, Product> catalog = new HashMap<>();
        catalog.put("prod_1", phone1);
        catalog.put("prod_2", phone2);
        this.cart = new ShoppingCart(catalog);
    }

    @Test
    void test_add_new_product() {
        cart.AddProduct("prod_1", 2);
        
        assertEquals(Map.of("prod_1", 2), cart.getItems());
    }

    @Test
    void test_add_existing_product() {
        cart.AddProduct("prod_1", 2);
        cart.AddProduct("prod_1", 1);
        assertEquals(Map.of("prod_1", 3), cart.getItems());
    }

    // @ParameterizedTest
    // @ValueSource(ints = {0, -1, -100})
    // void addNonPositiveQuantityThrows(int qty) {
    //     assertThrows(IllegalArgumentException.class, () -> cart.add("prod_102", qty));
    // }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void test_add_zero_quantity(int qty){
        assertThrows(InvalidQuantityException.class, () -> cart.AddProduct("prod_1", qty));
    }

    @Test 
    void test_add_unknown_product(){
        assertThrows(ProductNotFoundException.class, () -> cart.AddProduct("unknown", 1));
    }

    @Test 
    void test_add_more_than_stock(){
        assertThrows(InsufficientStockException.class, () -> cart.AddProduct("prod_1", 6));
    }

    @Test 
    void test_add_exceeding_stock_with_existing(){
        cart.AddProduct("prod_1", 3);
        assertThrows(InsufficientStockException.class, () -> cart.AddProduct("prod_1", 3));
    }

    @Test 
    void test_remove_full(){
        cart.AddProduct("prod_1", 3);
        cart.RemoveProduct("prod_1");
        assertEquals(Map.of(), cart.getItems());
    }

    @Test 
    void test_remove_partial(){
        cart.AddProduct("prod_1", 3);
        cart.RemoveProduct("prod_1",1);
        assertEquals(Map.of("prod_1", 2), cart.getItems());
    }

    @Test 
    void test_remove_more_than_in_cart_removes_all(){
        cart.AddProduct("prod_1", 3);
        cart.RemoveProduct("prod_1",5);
        assertEquals(Map.of(), cart.getItems());
    }

    @Test 
    void test_remove_exact_amount(){
        cart.AddProduct("prod_1", 2);
        cart.RemoveProduct("prod_1",2);
        assertEquals(Map.of(), cart.getItems());
    }

    @Test 
    void test_remove_unknown_product(){
        assertThrows(ProductNotFoundException.class, () -> cart.RemoveProduct("prod_1"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void test_remove_zero_negative(int qty){
        cart.AddProduct("prod_1", 1);
        assertThrows(ProductNotFoundException.class, () -> cart.RemoveProduct("prod_1",qty));
    }

    @Test 
    void test_total_price_empty(){
        assertEquals(0.0, cart.TotalPrice());
    }

    @Test 
    void test_total_price_one_product(){
        cart.AddProduct("prod_1", 2);
        assertEquals(2000.0, cart.TotalPrice());
    }

    @Test 
    void test_total_price_multiple(){
        cart.AddProduct("prod_1", 1);
        cart.AddProduct("prod_2", 3);
        assertEquals(2500.0, cart.TotalPrice());
    }

    @Test 
    void test_total_price_one_product_promocode(){
        cart.AddProduct("prod_1", 2);
        cart.ApplyPromo("SALE10");
        assertEquals(1800.0, cart.TotalPrice());
        cart.ApplyPromo("SALE20");
        assertEquals(1600.0, cart.TotalPrice());
    }

    @Test 
    void test_total_price_multiple_promocode(){
        cart.AddProduct("prod_1", 1);
        cart.AddProduct("prod_2", 3);
        cart.ApplyPromo("SALE10");
        assertEquals(2250.0, cart.TotalPrice());
        cart.ApplyPromo("SALE20");
        assertEquals(2000.0, cart.TotalPrice());
    }

    @Test 
    void test_promocode_apply_error(){
        assertThrows(InvalidPromoCodeException.class, () -> cart.ApplyPromo("SALE100"));
    }

    @Test 
    void test_items_returns_copy(){
        cart.AddProduct("prod_1", 1);

        Map<String,Integer> cop = cart.getItems();

        cop.put("prod_1", 999);
        assertEquals(1, cart.getItems().get("prod_1"));
    }


}
