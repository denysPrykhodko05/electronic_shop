<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file = "header.jsp"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Order in process</title>
</head>
  <body>
      <fmt:message key="your_order"/> №${orderId} <fmt:message key="in_process"/><br>
      <a href="/"><fmt:message key="home_page"/></a>
  </body>
</html>