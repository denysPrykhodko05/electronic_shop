<%@ attribute name="list"  type="java.util.List"%>
<%@ attribute name="checkedList"  type="java.util.List"%>
<%@ attribute name="parameterName"  type="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ tag import="java.util.ArrayList"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

 <c:choose>
     <c:when test="${not empty checkedList}">

         <c:forEach var="element" items="${list}">

               <c:choose>

                 <c:when test="${fn:contains(checkedList, element)}">
                   <input form="amountOfProductsForm" type="checkbox" name="${parameterName}" value="${element}" checked="checked"> ${element}<br>
                 </c:when>

                 <c:otherwise>
                      <input form="amountOfProductsForm" type="checkbox" name="${parameterName}" value="${element}" > ${element}<br>
                 </c:otherwise>

               </c:choose>

         </c:forEach>

     </c:when>

     <c:otherwise>

        <c:forEach var="element" items="${list}">
          <input form="amountOfProductsForm" type="checkbox" name="${parameterName}" value="${element}" > ${element}<br>
        </c:forEach>

     </c:otherwise>
 </c:choose>