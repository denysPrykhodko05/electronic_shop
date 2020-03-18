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

import com.epam.prykhodko.bean.FilterBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class ProductFilterCreateUtil {

    private ProductFilterCreateUtil() {

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

    public static String makeQueryFilterForProducts(FilterBean filterBean) {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append(GET_ALL_PRODUCTS)
            .append(WHERE)
            .append(PRICE_BETWEEN)
            .append(filterBean.getMinPrice())
            .append(AND).append(filterBean.getMaxPrice())
            .append(STRING_CLOSE_CIRCLE_BRACKET);
        manufactureFilter(filterBean.getChoosenManufactureList(), filterBean.getManufactures(), sqlQuery);
        categoryFilter(filterBean.getChossenCategoryList(), filterBean.getCategories(), sqlQuery);
        sqlQuery.append(filterBean.getSortQuery());
        return sqlQuery.toString();
    }

    public static String makeSortQueryForProducts(String parameter) {
        Map<String, String> queryMap = initQueryMap();
        return queryMap.getOrDefault(parameter, GET_ALL_PRODUCTS_FROM_LOW_PRICE);
    }

    public static String queryToFindProductsForPage(String query, int pageNumber, int amountProductsOnPage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(query)
            .append(LIMIT)
            .append(amountProductsOnPage * pageNumber - amountProductsOnPage)
            .append(COMMA).append(amountProductsOnPage * pageNumber);
        return stringBuilder.toString();
    }

    private static Map<String, String> initQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(BY_PRICE_FROM_LOW, GET_ALL_PRODUCTS_FROM_LOW_PRICE);
        queryMap.put(BY_PRICE_FROM_HIGH, GET_ALL_PRODUCTS_FROM_HIGH_PRICE);
        queryMap.put(BY_ALPHABETICAL_FROM_A_Z, GET_ALL_PRODUCTS_FROM_A_Z);
        queryMap.put(BY_ALPHABETICAL_FROM_Z_A, GET_ALL_PRODUCTS_FROM_Z_A);
        return queryMap;
    }

    private static void categoryFilter(List<String> choosenCategory, List<String> allcategories, StringBuilder sqlQuery) {
        boolean firstCategory = false;

        if (Objects.isNull(choosenCategory)) {
            choosenCategory = allcategories;
        }

        sqlQuery.append(AND);

        for (String c : choosenCategory) {

            if (!firstCategory) {
                sqlQuery.append(PRODUCT_CATEGORY_NAME).append(c).append(STRING_SINGLE_QUOTATION_MARK);
                firstCategory = true;
                continue;
            }

            sqlQuery.append(OR_WITH_PRODUCT_CATEGORY_NAME).append(c).append(STRING_SINGLE_QUOTATION_MARK);
        }

        sqlQuery.append(STRING_CLOSE_CIRCLE_BRACKET);
    }

    private static void manufactureFilter(List<String> manufacture, List<String> allManufactures, StringBuilder sqlQuery) {
        boolean firstManufacture = false;

        if (Objects.isNull(manufacture)) {
            manufacture = allManufactures;
        }

        sqlQuery.append(AND)
            .append(STRING_OPEN_CIRCLE_BRACKET);

        for (String e : manufacture) {

            if (!firstManufacture) {
                sqlQuery.append(MANUFACTURE_PARAMETER)
                    .append(e)
                    .append(STRING_SINGLE_QUOTATION_MARK);
                firstManufacture = true;
                continue;
            }
            sqlQuery.append(OR_WITH_MANUFACTURE)
                .append(e)
                .append(STRING_SINGLE_QUOTATION_MARK);
        }

        sqlQuery.append(")");
    }

    public static FilterBean setFilterBean(List<String> manufactures, List<String> categories, HttpServletRequest req) {
        FilterBean filterBean = new FilterBean();
        filterBean.setManufactures(manufactures);
        filterBean.setCategories(categories);
        filterBean.setFilterBean(req);
        return filterBean;
    }

    public static String combineFilterSettings(String amountFromForm,HttpServletRequest req, FilterBean filterBean){
        ServletContext servletContext = req.getServletContext();
        if (Objects.isNull(amountFromForm)) {
            amountFromForm = servletContext.getInitParameter(DEFAULT_PRODUCTS_ON_PAGE);
        }

        ProductFilterCreateUtil.setAmountOfProducts(req, amountFromForm);
        String sortQuery = ProductFilterCreateUtil.makeSortQueryForProducts(filterBean.getCurrentSort());
        filterBean.setSortQuery(sortQuery);
        return ProductFilterCreateUtil.makeQueryFilterForProducts(filterBean);
    }
}
