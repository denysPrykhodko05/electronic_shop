package com.epam.prykhodko.service.orderserviceimpl;

import com.epam.prykhodko.dao.OrderDAO;
import com.epam.prykhodko.entity.Order;
import com.epam.prykhodko.handler.TransactionHandler;
import com.epam.prykhodko.service.OrderService;

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
