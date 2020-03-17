package com.epam.prykhodko.jsptag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print("hello");
       /*
        out.print("<c:set var=\"pageNumber\" value=\"${1}\"/>\n"
            + "            <c:forEach begin=\"1\" end=\"${pageAmount}\">\n"
            + "               <button form=\"amountOfProductsForm\" name=\"page\" value=\"${pageNumber}\">\n"
            + "                   ${pageNumber}\n"
            + "                   <c:set var=\"pageNumber\" value=\"${pageNumber+1}\"/>\n"
            + "               </button>\n"
            + "            </c:forEach>\n"
            + "            <div id=\"contentHalder\">\n"
            + "               <productTag:ProductViewTag products=\"${all_product_list}\" amountOfProducts=\"${amountOfProducts}\"/>\n"
            + "            </div>\n"
            + "        </div>");
        */

    }
}
