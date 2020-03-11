package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT_OF_PRODUCTS;

import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class ProductViewUtil {

    public void setAmountOfProducts(HttpServletRequest httpServletRequest, String amount) {
        ServletContext servletContext = httpServletRequest.getServletContext();

        if (Objects.nonNull(amount)) {
            httpServletRequest.setAttribute(AMOUNT_OF_PRODUCTS, amount);
            return;
        }

        String defaultAmountOfProducts = servletContext.getInitParameter("defaultProductsOnPage");
        httpServletRequest.setAttribute(AMOUNT_OF_PRODUCTS, defaultAmountOfProducts);
    }
}
