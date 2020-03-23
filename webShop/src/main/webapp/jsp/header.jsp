<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="login" uri="/tld/LoginTag.tld"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
  <link href="styles\homePage.css" rel="stylesheet">
  <meta charset="UTF-8">
</head>

<body>
<div id="header">
  <div id="shopName"><a href="/">Electronics store</a></div>
  <div class="menu">
    <a href="/products">Products</a>
    <c:choose>
    <c:when test="${empty cartSize}">
      <a href="/cart" id="cartRef">Cart</a>
    </c:when>
    <c:otherwise>
      <a href="/cart" id="cartRef">Cart(${cartSize})</a>
    </c:otherwise>
    </c:choose>
    <a href="">Something more</a>
  </div id="loginTag">
     <login:LoginTag userLogin="${userLogin}"/>
  </div>
</div>
</html>