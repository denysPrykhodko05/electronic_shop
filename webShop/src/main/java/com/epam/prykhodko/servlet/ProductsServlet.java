package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.ALL_PRODUCT_LIST;
import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT_OF_PRODUCTS_FROM_FORM;
import static com.epam.prykhodko.constants.ApplicationConstants.CATEGORIES;
import static com.epam.prykhodko.constants.ApplicationConstants.DEFAULT_PRODUCTS_ON_PAGE;
import static com.epam.prykhodko.constants.ApplicationConstants.MANUFACTURES;
import static com.epam.prykhodko.constants.ApplicationConstants.PAGE;
import static com.epam.prykhodko.constants.ApplicationConstants.PAGE_AMOUNT;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_JSP;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_QUERY;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_SERVICE;
import static com.epam.prykhodko.util.ProductViewUtil.setAmountOfProducts;
import static com.epam.prykhodko.util.ProductViewUtil.queryToFindProductsForPage;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.DAOServiceProduct;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        String queryForFilter = (String) req.getAttribute(PRODUCT_QUERY);
        String pageNumberStr = req.getParameter(PAGE);
        String amountFromForm = req.getParameter(AMOUNT_OF_PRODUCTS_FROM_FORM);
        DAOServiceProduct<Product, Object> productDAOService = (DAOServiceProduct<Product, Object>) servletContext.getAttribute(PRODUCT_SERVICE);
        List<Product> products = productDAOService.getFilteredProducts(queryForFilter);
        List<String> manufactures = productDAOService.getAllManufactures();
        List<String> categories = productDAOService.getAllCategories();
        int amountOfProducts = products.size();

        if (Objects.isNull(amountFromForm)) {
            amountFromForm = servletContext.getInitParameter(DEFAULT_PRODUCTS_ON_PAGE);
        }

        if (Objects.isNull(pageNumberStr)) {
            pageNumberStr = String.valueOf(INTEGER_ONE);
        }

        int pageNumber = Integer.parseInt(pageNumberStr);
        int amountOnPage = Integer.parseInt(amountFromForm);
        int pageAmount = amountOfProducts / Integer.parseInt(amountFromForm);

        if (pageAmount > INTEGER_ZERO && amountOfProducts % Integer.parseInt(amountFromForm) != INTEGER_ZERO) {
            pageAmount++;
        }

        queryForFilter = queryToFindProductsForPage(queryForFilter, pageNumber, amountOnPage);

        products = productDAOService.getFilteredProducts(queryForFilter);

        req.setAttribute(MANUFACTURES, manufactures);
        req.setAttribute(CATEGORIES, categories);
        req.setAttribute(PAGE_AMOUNT, pageAmount);
        req.setAttribute(ALL_PRODUCT_LIST, products);
        setAmountOfProducts(req, amountFromForm);
        req.getRequestDispatcher(PRODUCT_JSP).forward(req, resp);
    }
}
