<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="cabinet"/></title>
</head>
<body>

<div class="container">
    <c:import url="/WEB-INF/header.jsp"/>
    <div class="row ">
        <div class="col-9">
            <div class="row">
                <div class="col fs-5">
                    <fmt:message key="books"/>
                </div>
            </div>
                <table class="table">
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="author"/></th>
                        <th><fmt:message key="returnDate"/></th>
                        <th><fmt:message key="place"/></th>
                        <th><fmt:message key="status"/></th>
                        <th><fmt:message key="fine"/></th>
                        <th></th>
                    </tr>

                    <c:forEach items="${list}" var="booklist">
                        <tr>
                            <td>${booklist.name}</td>
                            <td>${booklist.author}</td>
                            <td>${booklist.returnDate}</td>
                            <td>${booklist.place}</td>
                            <td>${booklist.status}</td>
                            <td>${booklist.fine}</td>

                            <td>
                            <c:choose>
                                <c:when test="${booklist.status == 'confirmed'}">
                                    <form action="${pageContext.request.contextPath}/library/returnBook">
                                        <input type="hidden" name="id" value="${booklist.id}"/>
                                        <input type="hidden" name="bookId" value="${booklist.bookId}"/>
                                        <button class="btn btn-outline-dark btn-sm">
                                            <fmt:message key="returnBook"/>
                                        </button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-outline-dark btn-sm" disabled>
                                        <fmt:message key="returnBook"/>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
        </div>

        <div class="col">
            <ul class="list-group">
                <li class="list-group-item">patronymic : ${sessionScope.patronymic}</li>
                <li class="list-group-item">First name : ${sessionScope.firstname}</li>
                <li class="list-group-item">Second name: ${sessionScope.secondname}</li>
                <li class="list-group-item">Email      : ${sessionScope.email}</li>
            </ul>
        </div>

</div>

</body>
</html>
