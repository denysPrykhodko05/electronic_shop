<%@ attribute name="captchaKey"  type="java.lang.String"%>
<%@ attribute name="hidden"  type="java.lang.Boolean"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    	<img alt="your avatar" src=/captcha><br>
    	<input type="text" name="regCaptcha"><br>
    	<c:if test="${hidden}">
        <input type="hidden" name="hiddenField" value="${captchaKey}">
    	</c:if>
