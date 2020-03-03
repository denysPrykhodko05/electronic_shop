<%@ attribute name="errorMessage" type="java.lang.String"%>
<%@ attribute name="hiddenFieldValue" type="java.lang.String"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    	<img src=http://localhost:8080/captcha><br>
    	<c:if test="${not empty hiddenFieldValue}">
          <input type=hidden name=captcha_hidden value=${hiddenFieldValue}>
    	</c:if>
    	<input type="text" name="regCaptcha"><br>