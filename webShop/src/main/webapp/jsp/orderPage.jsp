<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file = "header.jsp"%>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link href="../styles/cart.css" rel="stylesheet">
    <link href="../styles/orderPage.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="../javascript/utils/OrderUtils.js"></script>
    <meta charset="UTF-8">
    <title>Confirm order</title>
</head>

<body>

    <!-- Show product from cart-->
    <div id="productholder">

        <c:forEach var="product" items="${cart.cart}">

            <div class="product" id="${product.key.id}">
                  <div id="productImg"><img src="/images/products/${product.key.category}/${product.key.name}.jpg"/></div>

                  <!--product name-->
                  <div id="productInfo">${product.key.description}<br>
                    <!-product price-->
                    Price: $ ${product.key.price}
                  </div>
            </div>

        </c:forEach>

    </div>

    <!-- form for input user payment information-->
    <form action="/makeOrder.do" method="POST">

        <!-- block for choose payment method-->
        <div id="PaymentDiv">
            <fmt:message key="choose_payment_method"/>:

            <input class="paymentMethod" type="radio" name="paymentMethod" value="card">
            <label><fmt:message key="card"/></label>

            <input class="paymentMethod" type="radio" name="paymentMethod" value="uponReceipt" checked="checked">
            <label><fmt:message key="upon_receipt_of_order"/></label>
        </div><br>

        <c:if test="${not empty errors}">
            <c:forEach var="entry" items="${errors}">
                   <p id="incorrectField">${entry.value}</p>
            </c:forEach>
        </c:if>

        <!-- block for credit card-->
        <div id="cardMethod">
          <label><fmt:message key="card_number"/>: </label> <input type="tel" name="cardNumber"
                 pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"><br>

          <label><fmt:message key="data_of_card"/>: </label><input type="text" name="dataOfCard" pattern="\d{2}/\d{2}"><br>

          <label>CVC: </label> <input name="CVC" type="number" pattern="\d{3}"><br>
        </div>
        <br><br>

        <!-- block for choose delivery method-->
        <div id="deliveryDiv">
            <fmt:message key="choose_delivery_method"/>:

            <input type="radio" name="deliveryMethod" value="post">
            <label><fmt:message key="by_post"/></label>

            <input type="radio" name="deliveryMethod" value="fromStore" checked="checked">
            <label><fmt:message key="from_store"/></label>
        </div>

        <input type="submit" id="submit" value="Submit">
       </form>
</body>