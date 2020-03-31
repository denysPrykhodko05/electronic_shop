<%@ attribute name="userLogin"  type="java.lang.String"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${pageContext.request.locale}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="electronicStore"/>

      <c:choose>
          <c:when test="${not empty userLogin}">
            <div id="profile"><img src=/avatarDraw.do alt="" width="50px" height="50px"><a href="/">${userLogin}<br></a><a href="/logout"><fmt:message key="logOut"/></a>
          </c:when>

          <c:otherwise>
             <div id="profile"><img src="images/profile.jpg" alt="" width="0" height="0"><a href="/login.do"><fmt:message key="logIn"/><br></a><a href="/registration.do"><fmt:message key="registration"/></a>
          </c:otherwise>
      </c:choose>