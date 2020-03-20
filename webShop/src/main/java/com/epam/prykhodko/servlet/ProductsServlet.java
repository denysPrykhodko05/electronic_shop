package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT_OF_PRODUCTS_FROM_FORM;
import static com.epam.prykhodko.constants.ApplicationConstants.CATEGORIES;
import static com.epam.prykhodko.constants.ApplicationConstants.DEFAULT_PRODUCTS_ON_PAGE;
import static com.epam.prykhodko.constants.ApplicationConstants.FILTERS;
import static com.epam.prykhodko.constants.ApplicationConstants.FILTER_QUERY;
import static com.epam.prykhodko.constants.ApplicationConstants.MANUFACTURES;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_JSP;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_SERVICE;

import com.epam.prykhodko.bean.FilterBean;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.util.ProductFilterCreateUtil;
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
        ProductService productDAOService = (ProductService) servletContext.getAttribute(PRODUCT_SERVICE);
        List<String> manufactures = productDAOService.getAllManufactures();
        List<String> categories = productDAOService.getAllCategories();
        String amountFromForm = req.getParameter(AMOUNT_OF_PRODUCTS_FROM_FORM);

        if (Objects.isNull(amountFromForm)) {
            amountFromForm = servletContext.getInitParameter(DEFAULT_PRODUCTS_ON_PAGE);
        }

        FilterBean filterBean = ProductFilterCreateUtil.setFilterBean(manufactures, categories, req);
        String filterQuery = ProductFilterCreateUtil.combineFilterSettings(amountFromForm, req, filterBean);
        req.setAttribute(FILTER_QUERY, filterQuery);
        req.setAttribute(FILTERS, filterBean);
        req.setAttribute(MANUFACTURES, manufactures);
        req.setAttribute(CATEGORIES, categories);
        req.setAttribute(AMOUNT_OF_PRODUCTS_FROM_FORM, amountFromForm);
        req.getRequestDispatcher(PRODUCT_JSP).forward(req, resp);
    }
}
