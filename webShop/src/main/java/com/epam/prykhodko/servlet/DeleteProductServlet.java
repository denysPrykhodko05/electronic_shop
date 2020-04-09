package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT;
import static com.epam.prykhodko.constants.ApplicationConstants.CART;
import static com.epam.prykhodko.constants.ApplicationConstants.CART_PRICE;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_ID;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;

import com.epam.prykhodko.entity.Cart;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deleteProductFromCart")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = new JsonObject();
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        String productId = req.getParameter(PRODUCT_ID);

        if (Objects.isNull(productId)) {
            setSize(session, jsonObject, cart);
            writer.write(jsonObject.toString());
            return;
        }

        if (cart.delete(Integer.parseInt(productId)) > INTEGER_MINUS_ONE) {
            jsonObject.addProperty(PRODUCT_ID, productId);
            jsonObject.addProperty(CART_PRICE, cart.cartPrice());
            setSize(session, jsonObject, cart);
            writer.write(jsonObject.toString());
            return;
        }
        setSize(session, jsonObject, cart);
        writer.write(jsonObject.toString());
    }

    private void setSize(HttpSession session, JsonObject jsonObject, Cart cart) {
        jsonObject.addProperty(AMOUNT, cart.size());
        session.setAttribute(AMOUNT, cart.size());
    }
}
