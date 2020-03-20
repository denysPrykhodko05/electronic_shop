package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.CART;
import static com.epam.prykhodko.constants.ApplicationConstants.CART_PRICE;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_ID;
import static com.epam.prykhodko.constants.ApplicationConstants.SUCCESS;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;

import com.epam.prykhodko.entity.Cart;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteProductFromCart")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        PrintWriter writer = resp.getWriter();
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        String productId = req.getParameter(PRODUCT_ID);

        if (Objects.isNull(productId)) {
            jsonObject.addProperty(SUCCESS, false);
            writer.write(jsonObject.toString());
            return;
        }

        if (cart.delete(Integer.parseInt(productId)) > INTEGER_MINUS_ONE) {
            jsonObject.addProperty(SUCCESS, true);
            jsonObject.addProperty(PRODUCT_ID, productId);
            jsonObject.addProperty(CART_PRICE, cart.cartPrice());
            writer.write(jsonObject.toString());
            return;
        }

        jsonObject.addProperty(SUCCESS, false);
        writer.write(jsonObject.toString());
    }
}
