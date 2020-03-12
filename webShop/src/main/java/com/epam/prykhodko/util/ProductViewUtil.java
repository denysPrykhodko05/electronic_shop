package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT_OF_PRODUCTS;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_PRODUCTS;

import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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

    public String applyFiltersForProducts(String[] manufacture, String minPrice, String maxPrice, String[] category) {
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

        return sqlQuery.toString();
    }
}
