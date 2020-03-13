package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.ALL_PRODUCT_LIST;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_JSP;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_SERVICE;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.DAOServiceProduct;
import com.epam.prykhodko.util.ProductViewUtil;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private DAOServiceProduct<Product, Object> productDAOService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        HttpSession session = req.getSession();
        ProductViewUtil productViewUtil = new ProductViewUtil();
        String queryForFilter = (String) req.getAttribute("productQuery");
        String pageNumberStr = req.getParameter("page");
        String amountFromForm = req.getParameter("amountOfProductsFromForm");
        productDAOService = (DAOServiceProduct<Product, Object>) servletContext.getAttribute(PRODUCT_SERVICE);
        List<Product> products = productDAOService.getFilteredProducts(queryForFilter);
        List<String> manufactures = productDAOService.getAllManufactures();
        List<String> categories = productDAOService.getAllCategories();
        int amountOfProducts = products.size();

        if (Objects.isNull(amountFromForm)) {
            amountFromForm = servletContext.getInitParameter("defaultProductsOnPage");
        }

        if (Objects.isNull(pageNumberStr)) {
            pageNumberStr = "1";
        }
        int pageNumber = Integer.parseInt(pageNumberStr);

        int amountOnPage = Integer.parseInt(amountFromForm);
        int pageAmount = amountOfProducts / Integer.parseInt(amountFromForm);

        if (pageAmount > 0 && amountOfProducts % Integer.parseInt(amountFromForm) != 0) {
            pageAmount++;
        }

        products = productViewUtil.subListOfProducts(products, pageNumber, amountOnPage, amountOfProducts);

        req.setAttribute("manufactures", manufactures);
        req.setAttribute("categories", categories);
        req.setAttribute(ALL_PRODUCT_LIST, products);
        productViewUtil.setAmountOfProducts(session, amountFromForm);
        req.getRequestDispatcher(PRODUCT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
