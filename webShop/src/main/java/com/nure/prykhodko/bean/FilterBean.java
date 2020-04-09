package com.nure.prykhodko.bean;

import static com.nure.prykhodko.constants.ApplicationConstants.CATEGORY;
import static com.nure.prykhodko.constants.ApplicationConstants.MANUFACTURE;
import static com.nure.prykhodko.constants.ApplicationConstants.MAX_PRICE;
import static com.nure.prykhodko.constants.ApplicationConstants.MIN_PRICE;
import static com.nure.prykhodko.constants.ApplicationConstants.SORT;
import static com.nure.prykhodko.constants.ApplicationConstants.STRING_ONE_MILLION;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class FilterBean {

    private List<String> manufactures;
    private List<String> categories;
    private static List<String> choosenManufactureList;
    private static List<String> chossenCategoryList;
    private static String minPrice;
    private static String maxPrice;
    private static String currentSort;
    private static String sortQuery;

    public static FilterBean setFilterBean(HttpServletRequest request) {
        FilterBean filterBean = new FilterBean();
        String[] manufactureReq = request.getParameterValues(MANUFACTURE);
        String[] categoryReq = request.getParameterValues(CATEGORY);
        filterBean.minPrice = request.getParameter(MIN_PRICE);
        filterBean.maxPrice = request.getParameter(MAX_PRICE);
        filterBean.currentSort = request.getParameter(SORT);

        if (Objects.nonNull(manufactureReq)) {
            filterBean.choosenManufactureList = Arrays.asList(manufactureReq);
        }

        if (Objects.nonNull(categoryReq)) {
            filterBean.chossenCategoryList = Arrays.asList(categoryReq);
        }

        if (Objects.isNull(filterBean.minPrice) || StringUtils.EMPTY.equals(filterBean.minPrice)) {
            filterBean.minPrice = String.valueOf(INTEGER_ONE);
        }

        if (Objects.isNull(filterBean.maxPrice)) {
            filterBean. maxPrice = STRING_ONE_MILLION;
        }

        if (Objects.isNull(filterBean.currentSort)) {
            ServletContext servletContext = request.getServletContext();
            filterBean.currentSort = servletContext.getInitParameter("productSortType");
        }
        return filterBean;
    }

    public List<String> getChoosenManufactureList() {
        return choosenManufactureList;
    }

    public List<String> getChossenCategoryList() {
        return chossenCategoryList;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public String getCurrentSort() {
        return currentSort;
    }

    public String getSortQuery() {
        return sortQuery;
    }

    public void setSortQuery(String sortQuery) {
        this.sortQuery = sortQuery;
    }

    public List<String> getManufactures() {
        return manufactures;
    }

    public void setManufactures(List<String> manufactures) {
        this.manufactures = manufactures;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
