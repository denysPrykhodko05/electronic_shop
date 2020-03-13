<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file = "header.jsp"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="productTag" uri="/tld/ProductViewTag.tld"%>
<html lang="en">

<head>
    <link href="../styles/homePage.css" rel="stylesheet">
    <link href="../styles/products.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Products</title>
</head>

<body>
    <form id="amountOfProductsForm" method="GET" action="/products">
        <div id="filter-settings">
        Amount of products on page: <input name="amountOfProductsFromForm" id="amountOfProductsOnPage" type="number" value="${amountOfProducts}"/>
        <input type="submit" value="ok"/>
        </div>
    </form>
    <form id="sort" method="GET" action="/products">
        <select name="sort" onchange="this.form.submit()">
            <c:if test="${not empty sortType}">
              <option value="current">${sortType}</option>
            </c:if>
           <option value="byPriceFromLow">Price from low to high</option>
           <option value="byPriceFromHigh">Price from high to low</option>
           <option value="byAlphabeticalFromA-Z">A-Z</option>
           <option value="byAlphabeticalFromZ-A">Z-A</option>
        </select>
    </form>
    <div id="common">
        <div id="left-column">

          <!-- form for filters-->
           <form method="GET" action="/products">
              <div id="filter-manufacture">
                  Manufacture<br>
                        <c:choose>
                            <c:when test="${not empty manufactureCheck}">
                                <c:forEach var="manufacture" items="${manufactures}">
                                      <c:choose>
                                        <c:when test="${fn:contains(manufactureCheck, manufacture)}">
                                          <input type="checkbox" name="manufacture" value="${manufacture}" checked="checked"> ${manufacture}<br>
                                        </c:when>
                                        <c:otherwise>
                                             <input type="checkbox" name="manufacture" value="${manufacture}" > ${manufacture}<br>
                                        </c:otherwise>
                                      </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                               <c:forEach var="manufacture" items="${manufactures}">
                                 <input type="checkbox" name="manufacture" value="${manufacture}" > ${manufacture}<br>
                               </c:forEach>
                            </c:otherwise>
                        </c:choose>
                  <br>
              </div>
              <div id="price-filter">
                  Price<br>
                  Min: <input id="minPrice" type="number" name="minPrice" value="${minPriceInput}"><br>Max: <input id="maxPrice" type="number" name="maxPrice" value="${maxPriceInput}"><br><br>
              </div>
              <div id="category-filter">
                  Category<br>
                  <c:choose>
                      <c:when test="${not empty categoryCheck}">
                          <c:forEach var="category" items="${categories}">
                                <c:choose>
                                  <c:when test="${fn:contains(categoryCheck, category)}">
                                    <input type="checkbox" name="category" value="${category}" checked="checked"> ${category}<br>
                                  </c:when>
                                  <c:otherwise>
                                       <input type="checkbox" name="category" value="${category}" > ${category}<br>
                                  </c:otherwise>
                                </c:choose>
                          </c:forEach>
                      </c:when>
                      <c:otherwise>
                         <c:forEach var="category" items="${categories}">
                           <input type="checkbox" name="category" value="${category}" > ${category}<br>
                         </c:forEach>
                      </c:otherwise>
                  </c:choose>
              </div>
              <br>
              <input type="submit" value=ok><br>
          </form>
        </div>
        <div id="center-column">
            <div id="contentHalder">
               <productTag:ProductViewTag products="${all_product_list}" amountOfProducts="${amountOfProducts}"/>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script defer src="..\javascript\constants\constants.js"></script>
    <script defer src="..\javascript\validators\AmountOfProductsValidator.js"></script>
</body>

</html>