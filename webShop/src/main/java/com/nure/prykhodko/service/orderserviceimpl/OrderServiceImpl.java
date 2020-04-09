package com.nure.prykhodko.service.orderserviceimpl;

import com.nure.prykhodko.dao.OrderDAO;
import com.nure.prykhodko.entity.Order;
import com.nure.prykhodko.handler.TransactionHandler;
import com.nure.prykhodko.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private TransactionHandler transactionHandler;

    public OrderServiceImpl(OrderDAO orderDAO, TransactionHandler transactionHandler) {
        this.orderDAO = orderDAO;
        this.transactionHandler = transactionHandler;
    }

    @Override
    public Order add(Order order) {
        return transactionHandler.invokeTransaction(()->orderDAO.add(order));
    }
}
