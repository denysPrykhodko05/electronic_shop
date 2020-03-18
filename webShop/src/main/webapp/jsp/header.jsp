<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="login" uri="/tld/LoginTag.tld"%>

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
    <a href="/cart" id="cartRef">Cart</a>
    <a href="">Something more</a>
  </div id="loginTag">
     <login:LoginTag userLogin="${userLogin}"/>
  </div>
</div>
</html>