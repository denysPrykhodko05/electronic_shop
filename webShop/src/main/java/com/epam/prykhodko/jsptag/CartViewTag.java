package com.epam.prykhodko.jsptag;

import static com.epam.prykhodko.constants.ApplicationConstants.CART;
import static com.epam.prykhodko.constants.JspTagsConstants.CLOSE_DIV_TAG;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_DIV_PRICE_TAG;

import com.epam.prykhodko.entity.Cart;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CartViewTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter jspWriter = getJspContext().getOut();
        HttpSession session = pageContext.getSession();
        Cart cart = (Cart) session.getAttribute(CART);

        if (Objects.isNull(cart)) {
            return;
        }

        int price = cart.cartPrice();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPEN_DIV_PRICE_TAG)
            .append(price)
            .append(CLOSE_DIV_TAG);
        jspWriter.print(stringBuilder.toString());
    }
}
