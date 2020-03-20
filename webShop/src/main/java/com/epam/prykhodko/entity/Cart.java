package com.epam.prykhodko.entity;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.entity.products.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Cart {

    private final Map<Product, Integer> cart = new HashMap<>();

    public Product add(Product product, int amountOfProducts) {
        cart.put(product, amountOfProducts);
        return product;
    }

    public Integer delete(int id) {
        Optional<Product> product = cart.keySet().stream().filter(e -> e.getId() == id).findFirst();
        if (product.isPresent()) {
            return cart.remove(product.get());
        }
        return -1;
    }

    public Set<Entry<Product, Integer>> getAll() {
        return cart.entrySet();
    }

    public int size() {
        return cart.values().stream().mapToInt(v -> v).sum();
    }

    public BigDecimal cartPrice() {
        final BigDecimal[] sum = {new BigDecimal(INTEGER_ZERO)};
        cart.entrySet().forEach((k) -> {
            if (Objects.nonNull(k)) {
                sum[0] = sum[0].add(k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue())));
            }
        });
        return sum[0];
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }
}
