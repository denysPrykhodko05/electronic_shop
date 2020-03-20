<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file = "header.jsp"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link href="../styles/cart.css" rel="stylesheet">
    <link href="../styles/orderPage.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>MakeOrder</title>
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
             </div>
        </c:forEach>
    </div>

    <div id="PaymentDiv">
        Choose payment method:
        <input class="paymentMethod" type="radio" name="paymentMethod" value="card">
        <label>Card</label>

        <input class="paymentMethod" type="radio" name="paymentMethod" value="uponReceipt">
        <label>Upon receipt of order</label>
    </div>

       <form action="/makeOrder" method="POST">
         <div id="deliveryDiv">
             Choose delivery method:

             <input type="radio" name="deliveryMethod" value="post">
             <label>By post</label>

             <input type="radio" name="deliveryMethod" value="fromStore">
             <label>From store</label>
         </div>
         <div id="cardMethod">
           <br><br><label>Card number: </label> <input type="tel" name="cardNumber"
                  pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"><br>
            <label>Data of card: </label><input type="text" name="dataOfCard" pattern="\d{2}/\d{2}"><br>
            <label>CVC: </label> <input name="CVC" type="number" pattern="\d{3}"><br>
         </div>
          <input type="submit" id="submit" value="Submit">
       </form>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="../javascript/utils/OrderUtils"></script>
</body>