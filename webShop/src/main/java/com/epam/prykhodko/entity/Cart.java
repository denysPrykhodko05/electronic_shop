package com.epam.prykhodko.entity;

import com.epam.prykhodko.entity.products.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Cart {

    private final Map<Product, Integer> cartMap = new HashMap<>();

    public Product add(Product product, int amountOfProducts) {
        cartMap.put(product, amountOfProducts);
        return product;
    }

    public Integer delete(int id) {
        Optional<Product> product = cartMap.keySet().stream().filter(e -> e.getId() == id).findFirst();
        if (product.isPresent()) {
            return cartMap.remove(product.get());
        }
        return -1;
    }

    public Set<Entry<Product, Integer>> getAll() {
        return cartMap.entrySet();
    }

    public int size() {
        return cartMap.values().stream().mapToInt(v -> v).sum();
    }

    public int cartPrice() {
        return cartMap.keySet().stream()
            .filter(Objects::nonNull)
            .mapToInt(p -> p.getPrice().intValue())
            .sum();
    }

    public Map<Product, Integer> getCart() {
        return cartMap;
    }
}
