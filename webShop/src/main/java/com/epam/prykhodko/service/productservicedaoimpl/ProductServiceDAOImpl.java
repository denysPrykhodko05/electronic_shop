package com.epam.prykhodko.service.productservicedaoimpl;

import static com.epam.prykhodko.constants.DBConstants.GET_ALL_CATEGORIES;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_MANUFACTURES;

import com.epam.prykhodko.dao.DAOProduct;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.handler.TransactionHandler;
import com.epam.prykhodko.mananger.ConnectionManager;
import com.epam.prykhodko.service.DAOServiceProduct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductServiceDAOImpl implements DAOServiceProduct<Product, Object> {

    private final DAOProduct productDAO;
    private final TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());

    public ProductServiceDAOImpl(DAOProduct productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product addByForm(Object form) {
        return null;
    }

    @Override
    public Product createEntity(Object form) {
        return null;
    }

    @Override
    public Product add(Product entity) {
        return transactionHandler.invokeTransaction(() -> productDAO.add(entity));
    }

    @Override
    public boolean deleteByName(String name) {
        return transactionHandler.invokeWithoutTransaction(() -> {
            Product product = getByName(name);
            return productDAO.delete(product);
        });
    }

    @Override
    public boolean delete(Product entity) {
        return productDAO.delete(entity);
    }

    @Override
    public Product getByName(String name) {
        List<Product> products = productDAO.getAll();
        Optional<Product> product = products.stream().filter(e -> e.getName().equals(name)).findFirst();
        return product.orElse(null);
    }

    @Override
    public boolean isContains(Product entity) {
        List<Product> products = productDAO.getAll();
        Optional<Product> userTemp = products.stream()
            .filter(e -> (e.getName().equals(entity.getName()) && e.getCategory().equals(entity.getCategory())))
            .findFirst();
        return Objects.nonNull(userTemp.orElse(null));
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
}
