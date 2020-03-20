package com.epam.prykhodko.dao.impl;

import static com.epam.prykhodko.constants.ApplicationConstants.CATEGORY;
import static com.epam.prykhodko.constants.ApplicationConstants.DESCRIPTION;
import static com.epam.prykhodko.constants.ApplicationConstants.ID;
import static com.epam.prykhodko.constants.ApplicationConstants.MANUFACTURE;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.PRICE;
import static com.epam.prykhodko.constants.DBConstants.ADD_PRODUCT;
import static com.epam.prykhodko.constants.DBConstants.DELETE_PRODUCT_BY_NAME;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS;
import static com.epam.prykhodko.constants.DBConstants.GET_PRODUCT_BY_ID;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_ADD_USER;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_DELETE_USER_BY_LOGIN;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_GET_ALL_PRODUCTS;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_GET_PRODUCT_BY_DEFINE_PARAMETER;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_GET_PRODUCT_BY_FILTERS;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_GET_PRODUCT_BY_ID;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.dao.ProductDAO;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.handler.ConnectionHolder;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ProductDAOImpl implements ProductDAO {

    private static final Logger LOGGER = Logger.getLogger(ProductDAOImpl.class);

    @Override
    public Product get(int id) {
        try (PreparedStatement pstm = ConnectionHolder.getConnection().prepareStatement(GET_PRODUCT_BY_ID)) {
            pstm.setInt(1, id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return parseResultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(ERR_CANNOT_GET_PRODUCT_BY_ID);
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(parseResultSetToProduct(resultSet));
            }
            return products;
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_GET_ALL_PRODUCTS);
        }
        return products;
    }

    @Override
    public Product add(Product product) {
        PreparedStatement pstmt = null;
        try {
            pstmt = ConnectionHolder.getConnection().prepareStatement(ADD_PRODUCT);
            fillPreparedStatementByProductData(pstmt, product);
            if (pstmt.executeUpdate() > INTEGER_ZERO) {
                return product;
            }
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_ADD_USER);
        }
        return null;
    }

    @Override
    public boolean delete(Product product) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(DELETE_PRODUCT_BY_NAME);) {
            preparedStatement.setString(1, product.getName());
            if (preparedStatement.executeUpdate() > INTEGER_ZERO) {
                return true;
            }
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_DELETE_USER_BY_LOGIN);
        }
        return false;
    }

    /**
     * @param query - query to database
     * @return specified parameter of {@link Product} from database, etc manufacture, categories
     */
    @Override
    public List<String> getDefineParameter(String query) {
        List<String> parameters = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parameters.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error(ERR_CANNOT_GET_PRODUCT_BY_DEFINE_PARAMETER);
        }
        return parameters;
    }

    @Override
    public List<Product> getFilteredEntity(String query) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(parseResultSetToProduct(resultSet));
            }
            return products;
        } catch (SQLException e) {
            LOGGER.error(ERR_CANNOT_GET_PRODUCT_BY_FILTERS);
        }
        return products;
    }

    private void fillPreparedStatementByProductData(PreparedStatement pstmt, Product product) throws SQLException {
        int i = 0;
        pstmt.setString(++i, product.getName());
        pstmt.setInt(++i, Integer.parseInt(product.getPrice().toString()));
        pstmt.setString(++i, product.getManufacturer());
        pstmt.setString(++i, product.getCategory());
    }

    private Product parseResultSetToProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID);
        BigDecimal price = new BigDecimal(resultSet.getInt(PRICE));
        String name = resultSet.getString(NAME);
        String manufacture = resultSet.getString(MANUFACTURE);
        String category = resultSet.getString(CATEGORY);
        String description = resultSet.getString(DESCRIPTION);
        return new Product(id, name, price, manufacture, category, description);
    }
}
