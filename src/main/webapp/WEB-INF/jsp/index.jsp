<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<%@ page session="false" %>
<html>
<head>
    <title>Menu</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body class="body">
<jsp:include page="header.jsp"/>
<div class="other">
    <a href="addperson">Add person</a><br>
    <a href="persons">Persons list</a>
</div>
</body>
</html>