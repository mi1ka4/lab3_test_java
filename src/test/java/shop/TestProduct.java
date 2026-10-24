
package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.lab3_test.shop.Product;

public class TestProduct {

    @Test
    void createProductValid() {
        Product p = new Product("prod_1", "Смартфон", 1000.0, 5);
        assertEquals("prod_1", p.getId());
        assertEquals("Смартфон", p.getName());
        assertEquals(1000.0, p.getPrice());
        assertEquals(5, p.getStock());
    }

    // @Test
    // void createProductNegativePrice() {
    //     assertThrows(IllegalArgumentException.class, () -> new Product("prod_1", "Смартфон", -1.0, 5));
    // }

    // @Test
    // void createProductNegativeStock() {
    //     assertThrows(IllegalArgumentException.class, () -> new Product("prod_1", "Смартфон", 100.0, -1));
    // }

    // @Test
    // void toStringContainsIdAndName() {
    //     Product p = new Product("prod_1", "Смартфон", 100.0, 2);
    //     String s = p.toString();
    //     assertTrue(s.contains("prod_1"));
    //     assertTrue(s.contains("Смартфон"));
    // }

    // @Test
    // void equalsComparesAllFields() {
    //     Product p1 = new Product("p", "n", 1.0, 1);
    //     Product p2 = new Product("p", "n", 1.0, 1);
    //     Product p3 = new Product("p", "n", 1.0, 2);

    //     assertEquals(p1, p2);
    //     assertNotEquals(p1, p3);
    // }

    // @Test
    // void equalsReturnsFalseForNonProduct() {
    //     Product p = new Product("p", "n", 1.0, 1);
    //     assertFalse(p.equals("not product"));
    //     assertFalse(p.equals(null));
    // }
}