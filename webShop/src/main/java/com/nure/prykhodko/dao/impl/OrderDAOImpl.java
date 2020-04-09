package com.nure.prykhodko.dao.impl;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

import com.nure.prykhodko.dao.OrderDAO;
import com.nure.prykhodko.entity.Order;
import com.nure.prykhodko.entity.OrderedItem;
import com.nure.prykhodko.handler.ConnectionHolder;
import com.nure.prykhodko.constants.DBConstants;
import com.nure.prykhodko.constants.LoggerMessagesConstants;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;

public class OrderDAOImpl implements OrderDAO {

    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);

    @Override
    public Order add(Order order) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(DBConstants.INSERT_INTO_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatementByOrder(order, preparedStatement);

            if (preparedStatement.executeUpdate() == 0) {
                LOGGER.info(LoggerMessagesConstants.ERR_CANNOT_ADD_ORDER);
                return null;
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                addOrderIdToOrderedProducts(resultSet, order);
            }

            if (Objects.nonNull(addOrderedProduct(order.getOrderedItems()))) {
                return order;
            }

        } catch (SQLException e) {
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_MAKE_ORDER);
        }
        return null;
    }

    @Override
    public List<OrderedItem> addOrderedProduct(List<OrderedItem> orderedItems) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(DBConstants.INSERT_INTO_ORDERED_PRDUCT)) {
            fillPreparedStatmentByOrderedProducts(orderedItems, preparedStatement);

            if (!preparedStatement.executeBatch().equals(Statement.EXECUTE_FAILED)) {
                return orderedItems;
            }

        } catch (SQLException e) {
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_ADD_ORDERED_PRODUCT);
        }
        return null;
    }

    private void fillPreparedStatementByOrder(Order order, PreparedStatement preparedStatement) throws SQLException {
        int i = 0;
        preparedStatement.setString(++i, order.getOrderStatus().toString());
        preparedStatement.setString(++i, order.getDescription());
        preparedStatement.setDate(++i, Date.valueOf(order.getDate()));
        preparedStatement.setString(++i, order.getUserEmail());
    }

    private void addOrderIdToOrderedProducts(ResultSet resultSet, Order order) throws SQLException {
        for (OrderedItem o : order.getOrderedItems()) {
            o.setOrderId(resultSet.getInt(INTEGER_ONE));
        }
    }

    private void fillPreparedStatmentByOrderedProducts(List<OrderedItem> orderedItems, PreparedStatement preparedStatement) throws SQLException {
        for (OrderedItem op : orderedItems) {
            int i = 0;
            preparedStatement.setInt(++i, op.getProductId());
            preparedStatement.setInt(++i, op.getOrderId());
            preparedStatement.setBigDecimal(++i, op.getPrice());
            preparedStatement.setInt(++i, op.getAmount());
            preparedStatement.addBatch();
        }
    }
}
