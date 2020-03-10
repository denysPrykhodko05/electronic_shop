package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_JSP;

import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.dao.impl.ProductDAO;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.DAOService;
import com.epam.prykhodko.service.productservicedaoimpl.ProductServiceDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private DAO<Product> productDAO;
    private DAOService<Product> productDAOService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productDAO = new ProductDAO();
        productDAOService = new ProductServiceDAOImpl(productDAO);
        productDAOService.getAll();
        req.getRequestDispatcher(PRODUCT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
