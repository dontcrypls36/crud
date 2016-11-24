<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:url var="logouturl" value="/logout"/>
<div class="header">
    <sec:authorize access="hasAuthority('ADMIN')">
        <div class="menu"><a href="<c:url value="/menu"/>">Menu</a></div>
    </sec:authorize>
    <div class="logout"><a href="${logouturl}">Logout</a></div>
</div>

