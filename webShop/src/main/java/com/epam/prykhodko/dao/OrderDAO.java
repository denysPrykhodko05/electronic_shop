package com.epam.prykhodko.dao;

import com.epam.prykhodko.entity.Order;
import com.epam.prykhodko.entity.OrderedItem;
import java.util.List;

public interface OrderDAO {

    Order add(Order order);

    List<OrderedItem> addOrderedProduct(List<OrderedItem> orderedItem);
}