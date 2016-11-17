<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>Persons list</title>
</head>
<body>
<h3>Search by name</h3>
<c:url var="searchAction" value='/search/'/>
<form:form action="${searchAction}" method="get">
    <table>
        <tr>
            <td><label><spring:message text="Name"/></label></td>
            <td><input name="name" type="text" value="${searchName}"/></td>
        </tr>
        <tr>
            <c:if test="${empty searchName}">
                <td><input type="submit" name="action" value="<spring:message text="Search person"/>"/> </td>
            </c:if>
            <c:if test="${!empty searchName}">
                <td><input type="submit" name="action" value="<spring:message text="Reset search"/>"/> </td>
            </c:if>

        </tr>
    </table>
</form:form>

<h3>Users List</h3>
<table class="tg">
    <tr>
        <th width="80">User ID</th>
        <th width="120">User Name</th>
        <th width="20">Age</th>
        <th width="120">Create date</th>
        <th width="120">Login</th>
        <th width="120">Roles</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach var="person" items="${usersList}">
        <tr>
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.age}</td>
            <td>${person.createDate}</td>
            <td>${person.login}</td>
            <td><c:forEach var="role" items="${person.roles}">
                ${role},
            </c:forEach>
            </td>
            <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
            <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
        </tr>
    </c:forEach>
</table>
<%--<div class="pagination">--%>
    <%--<ul>--%>
        <%--<c:if test="${empty searchName}">--%>
            <%--<li><c:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<c:url value="/" ><c:param name="page" value="${p}"/>${p}</c:url>">${p}</a> </c:forEach></li>--%>
        <%--</c:if>--%>
        <%--<c:if test="${!empty searchName}">--%>
            <%--<li><c:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<c:url value="/search/?name=${searchName}&action=Search+person" ><c:param name="page" value="${p}"/>${p}</c:url>">${p}</a> </c:forEach></li>--%>
        <%--</c:if>--%>

    <%--</ul>--%>
<%--</div>--%>
<c:url var="deleteAllAction" value="/deleteAll"/>
<form:form action="${deleteAllAction}">
    <input type="submit" value="<spring:message text="Delete all"/>" />
</form:form>


</body>
</html>
