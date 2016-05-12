<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>Person Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Add a Person
</h1>

<c:url var="addAction" value='/person/add' />

<form:form action="${addAction}" modelAttribute="person">
    <table>
        <c:if test="${!empty person.name}">
            <tr>
                <td><form:label path="id"/></td>
                <td><form:input path="id" readonly="true" size="8"  disabled="true" /><form:hidden path="id" /></td>
            </tr>
        </c:if>
        <tr>
            <td><form:label path="name"><spring:message text="Name"/></form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="age"><spring:message text="Age"/> </form:label></td>
            <td><form:input path="age"/></td>
        </tr>
        <tr>
            <td><form:label path="admin"><spring:message text="Admin"/> </form:label></td>
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
<br>
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
<h3>Persons List</h3>
    <table class="tg">
        <tr>
            <th width="80">Person ID</th>
            <th width="120">Person Name</th>
            <th width="20">Age</th>
            <th width="30">Admin</th>
            <th width="120">Create date</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listPersons}" var="person">
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.age}</td>
                <td>${person.admin}</td>
                <td>${person.createDate}</td>
                <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
                <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
<div class="pagination">
    <ul>
        <c:if test="${empty searchName}">
            <li><c:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<c:url value="/" ><c:param name="page" value="${p}"/>${p}</c:url>">${p}</a> </c:forEach></li>
        </c:if>
        <c:if test="${!empty searchName}">
            <li><c:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<c:url value="/search/?name=${searchName}&action=Search+person" ><c:param name="page" value="${p}"/>${p}</c:url>">${p}</a> </c:forEach></li>
        </c:if>

    </ul>
</div>
<c:url var="deleteAllAction" value="/deleteAll"></c:url>
    <form:form action="${deleteAllAction}">
       <input type="submit" value="<spring:message text="Delete all"/>" />
    </form:form>
</body>
</html>