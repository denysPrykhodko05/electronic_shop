package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.CATEGORY;
import static com.epam.prykhodko.constants.ApplicationConstants.CATEGORY_CHECK;
import static com.epam.prykhodko.constants.ApplicationConstants.MANUFACTURE;
import static com.epam.prykhodko.constants.ApplicationConstants.MANUFACTURE_CHECK;
import static com.epam.prykhodko.constants.ApplicationConstants.MAX_PRICE;
import static com.epam.prykhodko.constants.ApplicationConstants.MAX_PRICE_INPUT;
import static com.epam.prykhodko.constants.ApplicationConstants.MIN_PRICE;
import static com.epam.prykhodko.constants.ApplicationConstants.MIN_PRICE_INPUT;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCT_QUERY;
import static com.epam.prykhodko.constants.ApplicationConstants.SORT;
import static com.epam.prykhodko.constants.ApplicationConstants.SORT_TYPE;
import static com.epam.prykhodko.constants.ApplicationConstants.STRING_ONE_MILLION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_PRODUCT_FILTER_DESTROY;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_PRODUCT_FILTER_INIT;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

import com.epam.prykhodko.util.ProductViewUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class ProductFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(ProductFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String[] manufacture = request.getParameterValues(MANUFACTURE);
        String[] category = request.getParameterValues(CATEGORY);
        String minPrice = request.getParameter(MIN_PRICE);
        String maxPrice = request.getParameter(MAX_PRICE);
        String currentSort = request.getParameter(SORT);
        List<String> manufacturelist = new ArrayList<>();
        List<String> categoryList = new ArrayList<>();

        if (Objects.nonNull(manufacture)) {
            manufacturelist = Arrays.asList(manufacture);
        }

        if (Objects.nonNull(category)) {
            categoryList = Arrays.asList(category);
        }

        if (Objects.isNull(minPrice)) {
            minPrice = String.valueOf(INTEGER_ONE);
        }

        if (Objects.isNull(maxPrice)) {
            maxPrice = STRING_ONE_MILLION;
        }

        ProductViewUtil productViewUtil = new ProductViewUtil();

        if (Objects.isNull(currentSort)) {
            currentSort = (String) session.getAttribute(SORT_TYPE);
        }

        String sortQuery = productViewUtil.makeSortQueryForProducts(currentSort);
        String filterQuery = productViewUtil.makeQueryFilterForProducts(manufacture, minPrice, maxPrice, category, sortQuery);

        httpServletRequest.setAttribute(MANUFACTURE_CHECK, manufacturelist);
        httpServletRequest.setAttribute(MIN_PRICE_INPUT, minPrice);
        httpServletRequest.setAttribute(MAX_PRICE_INPUT, maxPrice);
        httpServletRequest.setAttribute(CATEGORY_CHECK, categoryList);
        session.setAttribute(SORT_TYPE, currentSort);
        httpServletRequest.setAttribute(PRODUCT_QUERY, filterQuery);

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info(INFO_PRODUCT_FILTER_INIT);
    }

    @Override
    public void destroy() {
        LOGGER.info(INFO_PRODUCT_FILTER_DESTROY);
    }
}
