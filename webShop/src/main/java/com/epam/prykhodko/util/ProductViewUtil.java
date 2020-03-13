package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT_OF_PRODUCTS;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_A_Z;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_HIGH_PRICE;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_LOW_PRICE;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_Z_A;

import com.epam.prykhodko.entity.products.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.lang.Math;

public class ProductViewUtil {

    public void setAmountOfProducts(HttpSession session, String amount) {
        ServletContext servletContext = session.getServletContext();

        if (Objects.nonNull(amount)) {
            session.setAttribute(AMOUNT_OF_PRODUCTS, amount);
            return;
        }
        amount = (String) session.getAttribute(AMOUNT_OF_PRODUCTS);

        if (Objects.nonNull(amount)) {
            return;
        }

        String defaultAmountOfProducts = servletContext.getInitParameter("defaultProductsOnPage");
        session.setAttribute(AMOUNT_OF_PRODUCTS, defaultAmountOfProducts);
    }

    public String makeQueryFilterForProducts(String[] manufacture, String minPrice, String maxPrice, String[] category, String sort) {
        StringBuilder sqlQuery = new StringBuilder();
        boolean appended = false;
        sqlQuery.append(GET_ALL_PRODUCTS);

        if (Objects.nonNull(minPrice) && Objects.nonNull(maxPrice)) {

            sqlQuery.append(" WHERE ");

            sqlQuery.append(" (price between ").append(minPrice).append(" and ").append(maxPrice).append(")");
            appended = true;
        }

        if (Objects.nonNull(manufacture)) {
            boolean firstManufacture = false;

            if (appended) {
                sqlQuery.append(" and ");
            }

            sqlQuery.append(" (");

            for (String e : manufacture) {
                if (!firstManufacture) {
                    sqlQuery.append("manufacture='").append(e).append("'");
                    firstManufacture = true;
                    continue;
                }
                sqlQuery.append(" or manufacture='").append(e).append("'");
            }
            sqlQuery.append(")");
            appended = true;
        }

        if (Objects.nonNull(category)) {
            boolean firstCategory = false;
            if (appended) {
                sqlQuery.append(" and ");
            }

            for (String c : category) {
                if (!firstCategory) {
                    sqlQuery.append("(product_category.name = '").append(c).append("'");
                    firstCategory = true;
                    continue;
                }
                sqlQuery.append(" or product_category.name = '").append(c).append("'");
                appended = true;
            }
            sqlQuery.append(")");
        }
        sqlQuery.append(sort);
        return sqlQuery.toString();
    }

    public String makeSortQueryForProducts(String parameter) {
        Map<String, String> queryMap = initQueryMAp();
        String sort = queryMap.getOrDefault(parameter, GET_ALL_PRODUCTS_FROM_LOW_PRICE);
        return sort;
    }

    public List<Product> subListOfProducts(List<Product> productList, int pageNumber, int amountProductsOnPage, int amountOfProducts) {
        int diff = amountOfProducts-amountProductsOnPage;
        if(diff < 0){
            amountProductsOnPage = amountOfProduct - abs(diff);
        }
        return productList.subList(pageNumber - 1, amountProductsOnPage * pageNumber);
    }

    private Map<String, String> initQueryMAp() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("byPriceFromLow", GET_ALL_PRODUCTS_FROM_LOW_PRICE);
        queryMap.put("byPriceFromHigh", GET_ALL_PRODUCTS_FROM_HIGH_PRICE);
        queryMap.put("byAlphabeticalFromA-Z", GET_ALL_PRODUCTS_FROM_A_Z);
        queryMap.put("byAlphabeticalFromZ-A", GET_ALL_PRODUCTS_FROM_Z_A);
        return queryMap;
    }
}
