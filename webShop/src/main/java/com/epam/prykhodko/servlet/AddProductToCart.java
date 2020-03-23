package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT;
import static com.epam.prykhodko.constants.ApplicationConstants.CART;
import static com.epam.prykhodko.constants.ApplicationConstants.CART_PRICE;
import static com.epam.prykhodko.constants.ApplicationConstants.CART_SIZE;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_ID;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_SERVICE;

import com.epam.prykhodko.entity.Cart;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.ProductService;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddProductToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext servletContext = req.getServletContext();
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute(CART);
        ProductService productService = (ProductService) servletContext.getAttribute(PRODUCT_SERVICE);
        JsonObject jsonObject = new JsonObject();
        PrintWriter writer = resp.getWriter();
        String reqId = req.getParameter(PRODUCT_ID);
        String amount = req.getParameter(AMOUNT);

        if (Objects.isNull(reqId)) {
            writer.write(jsonObject.toString());
            return;
        }

        int id = Integer.parseInt(reqId);
        Product product = productService.getById(id);

        if (Objects.isNull(cart)) {
            cart = new Cart();
        }

        Optional<Product> productTemp = cart.getCart().keySet().stream().filter(e -> e.getId() == id).findFirst();
        if (Objects.isNull(amount) && productTemp.isPresent()) {
            amount = String.valueOf(cart.getCart().get(productTemp.get()));
            cart.add(product, Integer.parseInt(amount) + 1);
            setAttributes(session, jsonObject, cart);
            writer.write(jsonObject.toString());
            writer.close();
            return;
        }

        if (Objects.isNull(amount)) {
            amount = "1";
        }

        cart.add(product, Integer.parseInt(amount));
        setAttributes(session, jsonObject, cart);
        writer.write(jsonObject.toString());
        writer.close();
    }

    private void setAttributes(HttpSession session, JsonObject jsonObject, Cart cart) {
        session.setAttribute(CART, cart);
        session.setAttribute(CART_SIZE, cart.size());
        jsonObject.addProperty(AMOUNT, cart.size());
        jsonObject.addProperty(CART_PRICE, cart.cartPrice());
    }

}
