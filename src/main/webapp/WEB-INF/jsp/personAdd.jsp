<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url var="addAction" value='/add' />

<form:form action="${addAction}" modelAttribute="person">
    <table>
        <c:if test="${!empty person.name}">
            <tr>
                <td></td>
                <td><form:input path="id" readonly="true" size="8"  disabled="true" /><form:hidden path="id" /></td>
            </tr>
        </c:if>
        <tr>
            <td><label>Name:</label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><label path="age">Age:</label></td>
            <td><form:input path="age"/></td>
        </tr>
        <tr>
            <td><label path="admin">Admin:</label></td>
            <td><form:checkbox path="admin" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty person.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Person"/>" />
                </c:if>
                <c:if test="${empty person.name}">
                    <input type="submit"
                           value="<spring:message text="Add Person"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
