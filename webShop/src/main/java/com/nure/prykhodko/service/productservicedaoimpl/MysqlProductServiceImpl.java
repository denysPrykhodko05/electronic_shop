package com.nure.prykhodko.service.productservicedaoimpl;

import static com.nure.prykhodko.constants.DBConstants.GET_ALL_CATEGORIES;
import static com.nure.prykhodko.constants.DBConstants.GET_ALL_MANUFACTURES;

import com.nure.prykhodko.dao.ProductDAO;
import com.nure.prykhodko.entity.products.Product;
import com.nure.prykhodko.handler.TransactionHandler;
import com.nure.prykhodko.service.ProductService;
import java.util.List;

public class MysqlProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    private final TransactionHandler transactionHandler;

    public MysqlProductServiceImpl(ProductDAO productDAO, TransactionHandler transactionHandler) {
        this.productDAO = productDAO;
        this.transactionHandler = transactionHandler;
    }

    @Override
    public Product add(Product entity) {
        return transactionHandler.invokeTransaction(() -> productDAO.add(entity));
    }

    @Override
    public boolean delete(Product entity) {
        return transactionHandler.invokeTransaction(() -> productDAO.delete(entity));
    }

    @Override
    public List<Product> getAll() {
        return transactionHandler.invokeWithoutTransaction(productDAO::getAll);
    }

    @Override
    public List<String> getAllManufactures() {
        return transactionHandler.invokeWithoutTransaction(() -> productDAO.getDefineParameter(GET_ALL_MANUFACTURES));
    }

    @Override
    public List<String> getAllCategories() {
        return transactionHandler.invokeWithoutTransaction(() -> productDAO.getDefineParameter(GET_ALL_CATEGORIES));
    }

    @Override
    public List<Product> getFilteredProducts(String query) {
        return transactionHandler.invokeWithoutTransaction(() -> productDAO.getFilteredEntity(query));
    }

    @Override
    public Product getById(int id) {
        return transactionHandler.invokeWithoutTransaction(() -> productDAO.get(id));
    }
}
