<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="catalog"/></title>
</head>
<body>

<div class="container">
    <c:import url="/WEB-INF/header.jsp"/>
    <table class="table">
        <tr>
            <th>UserID</th>
            <th><fmt:message key="patronymic"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="author"/></th>
            <th><fmt:message key="place"/></th>
            <th><fmt:message key="fine"/></th>

        </tr>

        <c:forEach items="${list}" var="list">
            <tr>
                <td>${list.userId}</td>
                <td>${list.patronymic}</td>
                <td>${list.name}</td>
                <td>${list.author}</td>
                <td>${list.place}</td>
                <td>${list.fine}</td>

            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>