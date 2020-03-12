package com.epam.prykhodko.filter;

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

public class ProductFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String[] manufacture = request.getParameterValues("manufacture");
        String[] category = request.getParameterValues("category");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        List<String> manufacturelist = new ArrayList<>();
        List<String> categoryList = new ArrayList<>();

        if (manufacture != null) {
            manufacturelist = Arrays.asList(manufacture);
        }

        if (category != null) {
            categoryList = Arrays.asList(category);
        }

        if (Objects.isNull(minPrice)){
            minPrice = "1";
        }

        if (Objects.isNull(maxPrice)){
            maxPrice = "999999";
        }

        //TODO
        httpServletRequest.setAttribute("manufactureCheck", manufacturelist);
        httpServletRequest.setAttribute("minPriceInput", minPrice);
        httpServletRequest.setAttribute("maxPriceInput", maxPrice);
        httpServletRequest.setAttribute("categoryCheck", categoryList);

        ProductViewUtil productViewUtil = new ProductViewUtil();
        String query = productViewUtil.applyFiltersForProducts(manufacture, minPrice, maxPrice, category);
        httpServletRequest.setAttribute("productQuery", query);
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
