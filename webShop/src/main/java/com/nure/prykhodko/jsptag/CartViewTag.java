package com.nure.prykhodko.jsptag;

import com.nure.prykhodko.entity.Cart;
import com.nure.prykhodko.constants.ApplicationConstants;
import com.nure.prykhodko.constants.JspTagsConstants;
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
        Cart cart = (Cart) session.getAttribute(ApplicationConstants.CART);

        if (Objects.isNull(cart)) {
            return;
        }

        int price = cart.cartPrice();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(JspTagsConstants.OPEN_DIV_PRICE_TAG)
            .append(price)
            .append(JspTagsConstants.CLOSE_DIV_TAG);
        jspWriter.print(stringBuilder.toString());
    }
}
