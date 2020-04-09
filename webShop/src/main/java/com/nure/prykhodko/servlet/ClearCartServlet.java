package com.nure.prykhodko.servlet;

import com.nure.prykhodko.entity.Cart;
import com.google.gson.JsonObject;
import com.nure.prykhodko.constants.ApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/clearCart")
public class ClearCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute(ApplicationConstants.CART);
        HttpSession session = req.getSession();
        JsonObject jsonObject = new JsonObject();
        PrintWriter printWriter = resp.getWriter();

        if (Objects.isNull(cart)) {
            jsonObject.addProperty(ApplicationConstants.SUCCESS, false);
            printWriter.write(jsonObject.toString());
            return;
        }

        cart.getCart().clear();
        jsonObject.addProperty(ApplicationConstants.SUCCESS, true);
        session.removeAttribute(ApplicationConstants.AMOUNT);
        printWriter.write(jsonObject.toString());
    }
}
