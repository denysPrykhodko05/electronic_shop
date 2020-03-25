<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form>
  <select name="lang" onchange="this.form.submit()">
    <c:if test="${not empty localization}">
        <option value="${localization}">${localization}</option>
    </c:if>

    <c:forEach var="locale" items="${locales}">
      <c:if test="${locale ne localization}">
        <option value="${locale}">${locale}</option>
      </c:if>
    </c:forEach>
  </select>
</form>