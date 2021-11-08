<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title>Readers</title>
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/header.jsp"/>
        <div class="row ">
            <div class="col-9">
                <div class="row">
                    <div class="col fs-4">
                        <fmt:message key="librarian"/>
                    </div>
                </div>
                <table class="table">
                    <tr>
                        <th><fmt:message key="patronymic"/></th>
                        <th><fmt:message key="firstname"/></th>
                        <th><fmt:message key="secondname"/></th>
                        <th><fmt:message key="email"/></th>
                        <th></th>
                        <th></th>
                    </tr>

                    <c:forEach items="${list}" var="list">
                        <tr>
                            <td>${list.patronymic}</td>
                            <td>${list.firstname}</td>
                            <td>${list.secondname}</td>
                            <td>${list.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.blocked == true}">
                                        <form action="${pageContext.request.contextPath}/library/admin/blockUnblockReader">
                                            <input type="hidden" name="email" value="${list.email}"/>
                                            <input type="hidden" name="block" value="unblock"/>
                                            <button class="btn btn-outline-dark btn-sm">
                                                <fmt:message key="unblock"/>
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/library/admin/blockUnblockReader">
                                        <input type="hidden" name="email" value="${list.email}"/>
                                        <input type="hidden" name="block" value="block"/>
                                        <button class="btn btn-outline-dark btn-sm">
                                            <fmt:message key="block"/>
                                        </button>
                                    </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>

</body>
</html>
