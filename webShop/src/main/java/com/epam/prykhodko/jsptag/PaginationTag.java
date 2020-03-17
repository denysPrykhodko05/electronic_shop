package com.epam.prykhodko.jsptag;

import static com.epam.prykhodko.constants.ApplicationConstants.AMOUNT_OF_PRODUCTS_FROM_FORM;
import static com.epam.prykhodko.constants.ApplicationConstants.PAGE;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_SERVICE;
import static com.epam.prykhodko.constants.JspTagsConstants.BUY_BOTTON;
import static com.epam.prykhodko.constants.JspTagsConstants.CLOSE_DIV_TAG;
import static com.epam.prykhodko.constants.JspTagsConstants.CLOSE_IMAGE_TAG;
import static com.epam.prykhodko.constants.JspTagsConstants.CLOSE_TAG;
import static com.epam.prykhodko.constants.JspTagsConstants.CLOSE_TAG_BUTTON_FOR_PAGE;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_DIV_CONTENT;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_DIV_PRODUCT_DESCRIPTION;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_DIV_PRODUCT_IMG;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_DIV_PRODUCT_NAME;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_DIV_PRODUCT_PRICE;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_PRODUCT_IMAGE_TAG;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_TAG_BUTTON_FOR_PAGE;
import static com.epam.prykhodko.constants.JspTagsConstants.OPEN_TAG_DIV_CONTENT_HANDLER;
import static com.epam.prykhodko.constants.JspTagsConstants.SLASH;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.util.ProductFilterCreateUtil;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter writer = getJspContext().getOut();
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        ServletContext servletContext = req.getServletContext();
        String filterQuery = (String) req.getAttribute("FILTER_QUERY");
        String pageNumberStr = req.getParameter(PAGE);
        ProductService productDAOService = (ProductService) servletContext.getAttribute(PRODUCT_SERVICE);
        List<Product> products = productDAOService.getFilteredProducts(filterQuery);
        String amountFromForm = (String) req.getAttribute(AMOUNT_OF_PRODUCTS_FROM_FORM);
        int amountOfProducts = products.size();

        if (Objects.isNull(pageNumberStr)) {
            pageNumberStr = String.valueOf(INTEGER_ONE);
        }

        int pageNumber = Integer.parseInt(pageNumberStr);
        int amountOnPage = Integer.parseInt(amountFromForm);
        int pageAmount = amountOfProducts / Integer.parseInt(amountFromForm);

        if (pageAmount > INTEGER_ZERO && amountOfProducts % Integer.parseInt(amountFromForm) != INTEGER_ZERO) {
            pageAmount++;
        }

        filterQuery = ProductFilterCreateUtil.queryToFindProductsForPage(filterQuery, pageNumber, amountOnPage);

        products = productDAOService.getFilteredProducts(filterQuery);

        for (int i = 1; i <= pageAmount; i++) {
            writer.print(OPEN_TAG_BUTTON_FOR_PAGE + i + CLOSE_TAG + i + CLOSE_TAG_BUTTON_FOR_PAGE);
        }

        writer.print(OPEN_TAG_DIV_CONTENT_HANDLER);

        for (Product product : products) {
            writer.print(OPEN_DIV_CONTENT);
            writer.print(OPEN_DIV_PRODUCT_IMG);
            writer.print(OPEN_PRODUCT_IMAGE_TAG + product.getCategory() + SLASH + product.getName() + CLOSE_IMAGE_TAG);
            writer.print(CLOSE_DIV_TAG);
            writer.print(OPEN_DIV_PRODUCT_NAME);
            writer.print(product.getName());
            writer.print(CLOSE_DIV_TAG);
            writer.print(OPEN_DIV_PRODUCT_DESCRIPTION);
            writer.print(product.getDescription());
            writer.print(CLOSE_DIV_TAG);
            writer.print(OPEN_DIV_PRODUCT_PRICE);
            writer.print(product.getPrice());
            writer.print(CLOSE_DIV_TAG);
            writer.print(BUY_BOTTON);
            writer.print(CLOSE_DIV_TAG);
        }
        writer.print(CLOSE_DIV_TAG);
    }
}
