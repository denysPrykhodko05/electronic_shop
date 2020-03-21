<%@ attribute name="userLogin"  type="java.lang.String"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

      <c:choose>
          <c:when test="${not empty userLogin}">
            <div id="profile"><img src=/avatarDraw alt="" width="50px" height="50px"><a href="/">${userLogin}<br></a><a href="/logout">Log out</a>
          </c:when>

          <c:otherwise>
             <div id="profile"><img src="images/profile.jpg" alt="" width="0" height="0"><a href="/login">Log in<br></a><a href="/registration">Registration</a>
          </c:otherwise>
      </c:choose>