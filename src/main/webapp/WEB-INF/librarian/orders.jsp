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
    <div class="row">
        <div class="col fs-5">
            <fmt:message key="orders"/>
        </div>
    </div>
    <table class="table">

        <tr>
            <th>UserID</th>
            <th><fmt:message key="patronymic"/></th>
            <th><fmt:message key="author"/></th>
            <th><fmt:message key="place"/></th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach items="${list}" var="list">
            <tr>
                <td>${list.userId}</td>
                <td>${list.patronymic}</td>
                <td>${list.author}</td>
                <td>${list.place}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/library/librarian/confirm">
                        <input type="hidden" name="id" value="${list.id}"/>
                        <button class="btn btn-outline-dark btn-sm">
                            <fmt:message key="confirm"/>
                        </button>
                    </form>

                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/library/librarian/delete">
                        <input type="hidden" name="id" value="${list.id}"/>
                        <button class="btn btn-outline-dark btn-sm">
                            <fmt:message key="delete"/>
                        </button>
                    </form>

                </td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>