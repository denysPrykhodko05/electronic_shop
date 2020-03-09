package com.epam.prykhodko.service.productservicedaoimpl;

import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.handler.TransactionHandler;
import com.epam.prykhodko.mananger.ConnectionManager;
import com.epam.prykhodko.service.DAOService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductServiceDAOImpl implements DAOService<Product> {

    private final DAO<Product> productDAO;
    private final TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());

    public ProductServiceDAOImpl(DAO<Product> productDAO) {
        this.productDAO = productDAO;
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
}
