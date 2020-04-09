package com.nure.prykhodko.constants;

public abstract class JspTagsConstants {

    //Elements for pagination tag
    public static final String CLOSE_TAG = "\">";
    public static final String OPEN_TAG_BUTTON_FOR_PAGE = "<button form=\"amountOfProductsForm\" name=\"page\" value=\"";
    public static final String CLOSE_TAG_BUTTON_FOR_PAGE = "</button>";
    public static final String OPEN_TAG_DIV_CONTENT_HANDLER = "<div id=\"contentHalder\">";
    public static final String CLOSE_DIV_TAG = "</div>";
    public static final String OPEN_DIV_CONTENT = "<div class=\"content\">";
    public static final String OPEN_DIV_PRODUCT_IMG = "<div class=\"productImg\">";
    public static final String OPEN_DIV_PRODUCT_NAME = "<div class=\"product-name\">";
    public static final String OPEN_DIV_PRODUCT_DESCRIPTION = "<div class=\"product-description\">";
    public static final String OPEN_DIV_PRODUCT_PRICE = "<div class=\"product-price\">";
    public static final String OPEN_BUY_BOTTON = "<div><button class=\"buy-button\" data-id=\"";
    public static final String CLOSE_BUY_BOTTON = "\">Buy</button></div>";
    public static final String OPEN_PRODUCT_IMAGE_TAG = "<img src=\"/images/products/";
    public static final String SLASH = "/";
    public static final String CLOSE_IMAGE_TAG = ".jpg\">";

    //Elements for cart view tag
    public static final String OPEN_DIV_PRICE_TAG = "<div id=\"cartPrice\">price: ";
}