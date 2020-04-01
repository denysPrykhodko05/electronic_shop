<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file = "header.jsp"%>
<html lang="en">

<head>
    <link href="../styles/homePage.css" rel="stylesheet">
    <link href="../styles/regestration.css" rel="stylesheet">
    <link rel="import" href="../index.jsp">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script defer src="..\javascript\constants\constants.js"></script>
    <script defer src="..\javascript\validators\PasswordValidation.js"></script>
    <script defer src="..\javascript\validators\LoginAndEmailValidation.js"></script>
    <meta charset="UTF-8">
    <title>Log in</title>
</head>

<body>

    <form action="/login" id="reg" method="POST" name="regFrom" onsubmit="return dataValidation(password,password)">
        <div class="commonContainer">

            <!--block for credential fields-->
            <div class="container">

               <c:if test="${not empty errors}">
                   <c:forEach var="entry" items="${errors}">
                       <p id="incorrectField">${entry.value}</p>
                   </c:forEach>
                </c:if>

                <!--Login form-->
               <div data-tip="Login must consist from 3 to 16 symbols">
                   <input id="login" name="login" placeholder="Login" type="text" value="${login}">
               </div>

               <!--Password field-->
               <div data-tip="Password should have length from 8 to 16 symbols. Contains uppercase letter, specifie symbol and digit">
                   <input id="password" class="logIn-pass" name="password" placeholder="Password" type="password"><br>
               </div>

               <input type="submit" value="Ok"><br>

            </div>
        </div>
    </form>

</body>

</html>