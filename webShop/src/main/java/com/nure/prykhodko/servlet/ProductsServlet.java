package com.nure.prykhodko.servlet;

import com.nure.prykhodko.bean.FilterBean;
import com.nure.prykhodko.service.ProductService;
import com.nure.prykhodko.util.ProductFilterCreateUtil;
import com.nure.prykhodko.constants.ApplicationConstants;
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
        ProductService productDAOService = (ProductService) servletContext.getAttribute(ApplicationConstants.PRODUCT_SERVICE);
        List<String> manufactures = productDAOService.getAllManufactures();
        List<String> categories = productDAOService.getAllCategories();
        String amountFromForm = req.getParameter(ApplicationConstants.AMOUNT_OF_PRODUCTS_FROM_FORM);

        if (Objects.isNull(amountFromForm)) {
            amountFromForm = servletContext.getInitParameter(ApplicationConstants.DEFAULT_PRODUCTS_ON_PAGE);
        }

        FilterBean filterBean = ProductFilterCreateUtil.createFilterBeanFromRequest(manufactures, categories, req);
        String filterQuery = ProductFilterCreateUtil.combineFilterSettings(amountFromForm, req, filterBean);
        req.setAttribute(ApplicationConstants.FILTER_QUERY, filterQuery);
        req.setAttribute(ApplicationConstants.FILTERS, filterBean);
        req.setAttribute(ApplicationConstants.MANUFACTURES, manufactures);
        req.setAttribute(ApplicationConstants.CATEGORIES, categories);
        req.setAttribute(ApplicationConstants.AMOUNT_OF_PRODUCTS_FROM_FORM, amountFromForm);
        req.getRequestDispatcher(ApplicationConstants.PRODUCT_JSP).forward(req, resp);
    }
}
