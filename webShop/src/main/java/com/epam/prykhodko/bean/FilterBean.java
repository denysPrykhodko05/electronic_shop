package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.CATEGORY;
import static com.epam.prykhodko.constants.ApplicationConstants.MANUFACTURE;
import static com.epam.prykhodko.constants.ApplicationConstants.MAX_PRICE;
import static com.epam.prykhodko.constants.ApplicationConstants.MIN_PRICE;
import static com.epam.prykhodko.constants.ApplicationConstants.SORT;
import static com.epam.prykhodko.constants.ApplicationConstants.STRING_ONE_MILLION;
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
    private List<String> choosenManufactureList;
    private List<String> chossenCategoryList;
    private String minPrice;
    private String maxPrice;
    private String currentSort;
    private String sortQuery;

    public void setFilterBean(HttpServletRequest request) {
        String[] manufactureReq = request.getParameterValues(MANUFACTURE);
        String[] categoryReq = request.getParameterValues(CATEGORY);
        minPrice = request.getParameter(MIN_PRICE);
        maxPrice = request.getParameter(MAX_PRICE);
        currentSort = request.getParameter(SORT);

        if (Objects.nonNull(manufactureReq)) {
            choosenManufactureList = Arrays.asList(manufactureReq);
        }

        if (Objects.nonNull(categoryReq)) {
            chossenCategoryList = Arrays.asList(categoryReq);
        }

        if (Objects.isNull(minPrice) || StringUtils.EMPTY.equals(minPrice)) {
            this.minPrice = String.valueOf(INTEGER_ONE);
        }

        if (Objects.isNull(maxPrice)) {
            maxPrice = STRING_ONE_MILLION;
        }

        if (Objects.isNull(currentSort)) {
            ServletContext servletContext = request.getServletContext();
            currentSort = servletContext.getInitParameter("productSortType");
        }
    }

    public List<String> getChoosenManufactureList() {
        return choosenManufactureList;
    }

    public void setChoosenManufactureList(List<String> choosenManufactureList) {
        this.choosenManufactureList = choosenManufactureList;
    }

    public List<String> getChossenCategoryList() {
        return chossenCategoryList;
    }

    public void setChossenCategoryList(List<String> chossenCategoryList) {
        this.chossenCategoryList = chossenCategoryList;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCurrentSort() {
        return currentSort;
    }

    public void setCurrentSort(String currentSort) {
        this.currentSort = currentSort;
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
