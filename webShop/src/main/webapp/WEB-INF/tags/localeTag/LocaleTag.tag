<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select form="amountOfProductsForm" name="lang" onchange="this.form.submit()">
  <c:forEach var="locale" items="${locales}">
    <option value="${locale}">${locale}</option>
  </c:forEach>
</select>