<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form:form action="/login">
    <table>
        <tr>
            <td><label path="login">Login:</label></td>
            <td><input type="text" path="login"></td>
        </tr>
        <tr>
            <td><label path="password">Password:</label></td>
            <td><input type="text" path="password" m></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"></td>
            <td><input type="reset" value="Cancel"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
