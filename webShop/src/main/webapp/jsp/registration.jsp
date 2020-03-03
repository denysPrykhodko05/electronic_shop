<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="captcha" uri="/tld/CaptchaTag.tld"%>
<%@include file = "header.jsp"%>
<html lang="en">

<head>
    <link type="text/css" href="\styles\regestration.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Registration</title>
</head>

<body>
    <form action="/registration" id="reg" method="POST" name="regFrom" onsubmit="return dataValidation(password, confirmPassword)">
        <div class="commonContainer">
            <div class="container">
             <c:if test="${not empty errors}">
                <c:forEach var="entry" items="${errors}">
                       <p id="incorrectField">${entry.value}</p>
                </c:forEach>
             </c:if>
                <div data-tip="Name must be longer or equal one character symbol">
                    <input id="name" name="name" placeholder="Name" type="text" value="${userData["name"]}">
                </div>
                <div data-tip="Surname must be longer or equal one character symbol">
                    <input id="surname" name="surname" placeholder="Surname" type="text" value="${userData["surname"]}">
                </div>
                <div data-tip="Login must consist from 3 to 16 symbols">
                    <input id="login" name="login" placeholder="Login" type="text" value="${userData["login"]}">
                </div>
                <div data-tip="Email should be like 'email@example.com'">
                    <input id="email" name="email" placeholder="Email" type="text" value="${userData["email"]}"><br>
                </div>
                <div data-tip="Password should have length from 8 to 16 symbols. Contains uppercase letter, specifie symbol and digit">
                    <input id="password" name="password" placeholder="Password" type="password"><br>
                </div>
                <div data-tip="Must be equal for password">
                    <input id="confirmPassword" name="confirmPassword" placeholder="Confirm password" type="password"><br>
                </div>
                <captcha:CaptchaTag captchaKey="${captchaKey}" hidden="${hidden}"/>
                <input id="privacy-policy" type="checkbox" name="privacy-policy"> Agree with <a href="#myModal" id="privacy-policy-ref">User agreement</a><br><br>
                <input type="checkbox" name="mails"> Do you want receive mails?<br><br>
                <input type="submit" value="Ok"/><br>
            </div>
        </div>
    </form>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <p>User agreement</p>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script defer src="..\javascript\constants\constants.js"></script>
    <script defer src="..\javascript\validators\PasswordValidation.js"></script>
    <script defer src="..\javascript\validators\LoginAndEmailValidation.js"></script>
    <script defer src="..\javascript\utils\createModalWindow.js"></script>
</body>

</html>