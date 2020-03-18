package com.epam.prykhodko.entity;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.entity.products.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cart {

    private final Map<Product, Integer> cart = new HashMap<>();

    public Product add(Product product) {
        if (cart.containsKey(product)) {
            Integer amount = cart.get(product);
            amount++;
            cart.put(product, amount);
            return product;
        }

        cart.put(product, INTEGER_ONE);
        return product;
    }

    public Integer delete(Product product) {
        return cart.remove(product);
    }

    public Set<Entry<Product, Integer>> getAll() {
        return cart.entrySet();
    }

    public int size() {
        return cart.values().stream().mapToInt(v -> v).sum();
    }

    public BigDecimal cartPrice() {
        final BigDecimal[] sum = {new BigDecimal(INTEGER_ZERO)};
        cart.keySet().forEach(e -> sum[0] = sum[0].add(e.getPrice()));
        return sum[0];
    }
}
