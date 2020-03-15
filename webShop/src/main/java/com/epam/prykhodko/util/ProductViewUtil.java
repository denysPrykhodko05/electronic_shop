package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT_OF_PRODUCTS;
import static com.epam.prykhodko.constants.ApplicationConstants.BY_ALPHABETICAL_FROM_A_Z;
import static com.epam.prykhodko.constants.ApplicationConstants.BY_ALPHABETICAL_FROM_Z_A;
import static com.epam.prykhodko.constants.ApplicationConstants.BY_PRICE_FROM_HIGH;
import static com.epam.prykhodko.constants.ApplicationConstants.BY_PRICE_FROM_LOW;
import static com.epam.prykhodko.constants.ApplicationConstants.DEFAULT_PRODUCTS_ON_PAGE;
import static com.epam.prykhodko.constants.DBConstants.AND;
import static com.epam.prykhodko.constants.DBConstants.COMMA;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_A_Z;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_HIGH_PRICE;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_LOW_PRICE;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS_FROM_Z_A;
import static com.epam.prykhodko.constants.DBConstants.LIMIT;
import static com.epam.prykhodko.constants.DBConstants.MANUFACTURE_PARAMETER;
import static com.epam.prykhodko.constants.DBConstants.OR_WITH_MANUFACTURE;
import static com.epam.prykhodko.constants.DBConstants.OR_WITH_PRODUCT_CATEGORY_NAME;
import static com.epam.prykhodko.constants.DBConstants.PRICE_BETWEEN;
import static com.epam.prykhodko.constants.DBConstants.PRODUCT_CATEGORY_NAME;
import static com.epam.prykhodko.constants.DBConstants.STRING_CLOSE_CIRCLE_BRACKET;
import static com.epam.prykhodko.constants.DBConstants.STRING_OPEN_CIRCLE_BRACKET;
import static com.epam.prykhodko.constants.DBConstants.STRING_SINGLE_QUOTATION_MARK;
import static com.epam.prykhodko.constants.DBConstants.WHERE;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class ProductViewUtil {

    private ProductViewUtil() {

    }

    public static void setAmountOfProducts(HttpServletRequest request, String amount) {
        ServletContext servletContext = request.getServletContext();

        if (Objects.nonNull(amount)) {
            request.setAttribute(AMOUNT_OF_PRODUCTS, amount);
            return;
        }

        amount = (String) request.getAttribute(AMOUNT_OF_PRODUCTS);

        if (Objects.nonNull(amount)) {
            return;
        }

        String defaultAmountOfProducts = servletContext.getInitParameter(DEFAULT_PRODUCTS_ON_PAGE);
        request.setAttribute(AMOUNT_OF_PRODUCTS, defaultAmountOfProducts);
    }

    public static String makeQueryFilterForProducts(String[] manufacture, String minPrice, String maxPrice, String[] category, String sort) {
        StringBuilder sqlQuery = new StringBuilder();
        boolean appended = false;
        sqlQuery.append(GET_ALL_PRODUCTS);

        if (Objects.nonNull(minPrice) && Objects.nonNull(maxPrice)) {

            sqlQuery.append(WHERE);

            sqlQuery.append(PRICE_BETWEEN).append(minPrice).append(AND).append(maxPrice).append(STRING_CLOSE_CIRCLE_BRACKET);
            appended = true;
        }

        if (Objects.nonNull(manufacture)) {
            manufactureFilter(manufacture, appended, sqlQuery);
        }

        if (Objects.nonNull(category)) {
            categoryFilter(category, appended, sqlQuery);
        }
        sqlQuery.append(sort);
        return sqlQuery.toString();
    }

    public static String makeSortQueryForProducts(String parameter) {
        Map<String, String> queryMap = initQueryMAp();
        return queryMap.getOrDefault(parameter, GET_ALL_PRODUCTS_FROM_LOW_PRICE);
    }

    public static String queryToFindProductsForPage(String query, int pageNumber, int amountProductsOnPage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(query);
        stringBuilder.append(LIMIT).append(amountProductsOnPage * pageNumber - amountProductsOnPage).append(COMMA).append(amountProductsOnPage * pageNumber);
        return stringBuilder.toString();
    }

    private static Map<String, String> initQueryMAp() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(BY_PRICE_FROM_LOW, GET_ALL_PRODUCTS_FROM_LOW_PRICE);
        queryMap.put(BY_PRICE_FROM_HIGH, GET_ALL_PRODUCTS_FROM_HIGH_PRICE);
        queryMap.put(BY_ALPHABETICAL_FROM_A_Z, GET_ALL_PRODUCTS_FROM_A_Z);
        queryMap.put(BY_ALPHABETICAL_FROM_Z_A, GET_ALL_PRODUCTS_FROM_Z_A);
        return queryMap;
    }

    private static void categoryFilter(String[] category, boolean appended, StringBuilder sqlQuery) {
        boolean firstCategory = false;

        if (appended) {
            sqlQuery.append(AND);
        }

        for (String c : category) {

            if (!firstCategory) {
                sqlQuery.append(PRODUCT_CATEGORY_NAME).append(c).append(STRING_SINGLE_QUOTATION_MARK);
                firstCategory = true;
                continue;
            }

            sqlQuery.append(OR_WITH_PRODUCT_CATEGORY_NAME).append(c).append(STRING_SINGLE_QUOTATION_MARK);
        }

        sqlQuery.append(STRING_CLOSE_CIRCLE_BRACKET);
    }

    private static void manufactureFilter(String[] manufacture, boolean appended, StringBuilder sqlQuery) {
        boolean firstManufacture = false;

        if (appended) {
            sqlQuery.append(AND);
        }

        sqlQuery.append(STRING_OPEN_CIRCLE_BRACKET);

        for (String e : manufacture) {

            if (!firstManufacture) {
                sqlQuery.append(MANUFACTURE_PARAMETER).append(e).append(STRING_SINGLE_QUOTATION_MARK);
                firstManufacture = true;
                continue;
            }
            sqlQuery.append(OR_WITH_MANUFACTURE).append(e).append(STRING_SINGLE_QUOTATION_MARK);
        }

        sqlQuery.append(")");
    }
}
