<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="login" uri="/tld/LoginTag.tld"%>
<%@ taglib prefix="locale" uri="/tld/LocaleTag.tld"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${pageContext.request.locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="electronicStore"/>

<html lang="en">

<head>
  <link href="styles\homePage.css" rel="stylesheet">
  <meta charset="UTF-8">
</head>

<body>
<div id="header">
  <div id="shopName"><a href="/">Electronics store</a></div>
  <div class="menu">
    <a href="/products"><fmt:message key="products"/></a>
    <c:choose>
    <c:when test="${empty cartSize}">
      <a href="/cart" id="cartRef"><fmt:message key="cart"/></a>
    </c:when>
    <c:otherwise>
      <a href="/cart" id="cartRef"><fmt:message key="cart"/>(${cartSize})</a>
    </c:otherwise>
    </c:choose>
    <a href="">Something more</a>
  </div id="loginTag">
     <login:LoginTag userLogin="${userLogin}"/>
  </div>
</div>
  <locale:LocaleTag/>
</html>