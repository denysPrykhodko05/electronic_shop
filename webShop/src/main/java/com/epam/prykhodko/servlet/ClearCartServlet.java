package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.CART;
import static com.epam.prykhodko.constants.ApplicationConstants.SUCCESS;

import com.epam.prykhodko.entity.Cart;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clearCart")
public class ClearCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        JsonObject jsonObject = new JsonObject();
        PrintWriter printWriter = resp.getWriter();

        if (Objects.isNull(cart)) {
            jsonObject.addProperty(SUCCESS, false);
            printWriter.write(jsonObject.toString());
            return;
        }

        cart.getCart().clear();
        jsonObject.addProperty(SUCCESS, true);
        printWriter.write(jsonObject.toString());
    }
}
