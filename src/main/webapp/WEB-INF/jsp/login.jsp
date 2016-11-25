<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
<c:if test="${not empty error}"><div class="error">${error}</div></c:if>
<c:if test="${not empty logout}"><div class="success">${logout}</div></c:if>
<c:url value='/spring_security_check' var="loginUrl"/>
<form:form action="${loginUrl}" name="loginForm" method="POST">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="j_username" placeholder="Login"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="j_password" placeholder="Password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"></td>
            <td><input type="reset" value="Cancel"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
