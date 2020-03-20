package com.epam.prykhodko.jsptag;

import static com.epam.prykhodko.constants.ApplicationConstants.CART;

import com.epam.prykhodko.entity.Cart;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CartViewTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter jspWriter = getJspContext().getOut();
        HttpSession session = pageContext.getSession();
        Cart cart = (Cart) session.getAttribute(CART);

        if (Objects.isNull(cart)){
            return;
        }

        BigDecimal price = cart.cartPrice();
        jspWriter.print("<div id=\"cartPrice\">price: " + price + "</div>");
    }
}
