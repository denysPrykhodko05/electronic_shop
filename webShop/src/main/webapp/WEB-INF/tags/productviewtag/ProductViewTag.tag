<%@ attribute name="products"  type="java.util.List"%>
<%@ attribute name="amountOfProducts"  type="java.lang.Integer"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag import="java.util.ArrayList"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <c:choose>
     <c:when test="${not empty products}">
         <c:forEach var="product" items="${products}" begin="0" end="${amountOfProducts-1}">
           <div class="content">
                <div class="productImg">
                   <img src="/images/products/${product.category}/${product.name}.jpg">
                </div>
                <div class="product-name">
                   ${product.name}
                </div>
                <div class="product-description">
                   ${product.description}
                </div>
                <div class="product-price">
                   ${product.price}$
                </div>
                <button class="buy-button">Buy</button>
           </div>
         </c:forEach>
     </c:when>
     <c:otherwise>
        <div>No products</div>
     </c:otherwise>
 </c:choose>