<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file = "header.jsp"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cartView" uri="/tld/CartTag.tld" %>

<head>
    <link href="../styles/homePage.css" rel="stylesheet">
    <link href="../styles/cart.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Cart</title>
</head>

<body>
  <div id="productholder">
    <c:forEach var="product" items="${cart.cart}">
        <div class="product" id="${product.key.id}">
              <div id="productImg"><img src="/images/products/${product.key.category}/${product.key.name}.jpg"/></div>

              <!--product name-->
              <div id="productInfo">${product.key.description}<br>

              <!-product price-->
              Price: $ ${product.key.price}
              </div>

              <!--amount of products-->
              <div class="amountOfProductField">
                 <label>Amount: <input type="number" class="amountOfProduct" data-id="${product.key.id}" min="1" max="15" value="${product.value}"/></label>
              </div>

              <!--delete button-->
              <div id="deleteButton"><button class="delete-button" data-id="${product.key.id}">Delete</button></div>
         </div>
    </c:forEach>
  </div>
  <div id="productInfoBlock">
    <c:if test="${not empty cart.cart}">
        <cartView:CartView/>
        <div id="clearCart">
            <button id="clearCartButton">Clear cart</button>
        </div>
        <div id="makeOrder">
            <button id="makeOrderButton">Make order</button>
        </div>
     </c:if>
  </div>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script defer src="..\javascript\utils\CartUtil.js"></script>
</body>