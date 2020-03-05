<%@ attribute name="login"  type="java.lang.String"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <c:choose>
      <c:when test="${not empty login}">
        <div id="profile"><img src="images/profile.png" alt="" width="50px" height="50px"><a href="/">${login}<br></a><a href="/logout">Log out<br></a>
      </c:when>
      <c:otherwise>
        <div id="profile"><img src="images/profile.png" alt="" width="50px" height="50px"><a href="/login">Log in<br></a><a href="/registration">Registration</a>
      </c:otherwise>
  </c:choose>
