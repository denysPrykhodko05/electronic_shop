package com.nure.prykhodko.dao;

import com.nure.prykhodko.entity.Order;
import com.nure.prykhodko.entity.OrderedItem;
import java.util.List;

public interface OrderDAO {

    Order add(Order order);

    List<OrderedItem> addOrderedProduct(List<OrderedItem> orderedItem);
}