package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.ALL_PRODUCT_LIST;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_JSP;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_SERVICE;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.DAOService;
import com.epam.prykhodko.service.DAOServiceProduct;
import com.epam.prykhodko.util.ProductViewUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private DAOServiceProduct<Product, Object> productDAOService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        ProductViewUtil productViewUtil = new ProductViewUtil();
        String amountFromForm = req.getParameter("amountOfProductsFromForm");
        productDAOService = (DAOServiceProduct<Product, Object>) servletContext.getAttribute(PRODUCT_SERVICE);
        List<Product> products = productDAOService.getAll();
        List<String> manufactures = productDAOService.getAllManufactures();
        req.setAttribute("manufactures",manufactures);
        req.setAttribute(ALL_PRODUCT_LIST, products);
        productViewUtil.setAmountOfProducts(req, amountFromForm);
        req.getRequestDispatcher(PRODUCT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
