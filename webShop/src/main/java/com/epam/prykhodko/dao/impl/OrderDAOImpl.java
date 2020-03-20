package com.epam.prykhodko.dao.impl;

import static com.epam.prykhodko.constants.DBConstants.INSERT_INTO_ORDER;
import static com.epam.prykhodko.constants.DBConstants.INSERT_INTO_ORDERED_PRDUCT;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

import com.epam.prykhodko.dao.OrderDAO;
import com.epam.prykhodko.entity.Order;
import com.epam.prykhodko.entity.OrderedProduct;
import com.epam.prykhodko.handler.ConnectionHolder;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public Order add(Order order) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(INSERT_INTO_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatementByOrder(order, preparedStatement);
            if (preparedStatement.executeUpdate() == 0) {
                //TODO
                return null;
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                addOrderIdToOrderedProducts(resultSet, order);
            }
            if (Objects.nonNull(addOrderedProduct(order.getOrderedProducts()))) {
                return order;
            }
        } catch (SQLException e) {
            //TODO
            System.out.println("asd");
        }
        return null;
    }

    @Override
    public List<OrderedProduct> addOrderedProduct(List<OrderedProduct> orderedProducts) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(INSERT_INTO_ORDERED_PRDUCT)) {
            fillPreparedStatmentByOrderedProducts(orderedProducts, preparedStatement);
            if (!preparedStatement.executeBatch().equals(Statement.EXECUTE_FAILED)) {
                return orderedProducts;
            }
        } catch (SQLException e) {
            //TODO
        }
        return null;
    }

    private void fillPreparedStatementByOrder(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, order.getOrderStatus().toString());
        preparedStatement.setString(2, order.getDescription());
        preparedStatement.setDate(3, Date.valueOf(order.getDate()));
        preparedStatement.setString(4, order.getUserEmail());
    }

    private void addOrderIdToOrderedProducts(ResultSet resultSet, Order order) throws SQLException {
        for (OrderedProduct o : order.getOrderedProducts()) {
            o.setOrderId(resultSet.getInt(INTEGER_ONE));
        }
    }

    private void fillPreparedStatmentByOrderedProducts(List<OrderedProduct> orderedProducts, PreparedStatement preparedStatement) throws SQLException {
        for (OrderedProduct op : orderedProducts) {
            preparedStatement.setInt(1, op.getProductId());
            preparedStatement.setInt(2, op.getOrderId());
            preparedStatement.setBigDecimal(3, op.getPrice());
            preparedStatement.setInt(4, op.getAmount());
            preparedStatement.addBatch();
        }
    }
}
