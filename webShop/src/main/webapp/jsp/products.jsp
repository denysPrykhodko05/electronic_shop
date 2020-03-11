<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file = "header.jsp"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div id="common">
        <div id="left-column">
        <form method="" action="">
            <div id="filter-manufacture">
                Manufacture<br>
                <c:forEach var="manufacture" items="${manufactures}">
                     <input type="checkbox" name="manufactureCheckBox" value="${manufacture}"> ${manufacture}<br>
                </c:forEach>
                <br>
            </div>
            <div id="price-filter">
                Price<br>
                Min: <input type="number" name="minPrice"><br>Max: <input type="number" name="maxPrice"><br><br>
            </div>
            <div id="category-filter">
                Category<br>
                <input type="checkbox" name="category" value="notebook">Notebook<br>
                <input type="checkbox" name="category" value="smartphone">Smartphone<br><br>
            </div>
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