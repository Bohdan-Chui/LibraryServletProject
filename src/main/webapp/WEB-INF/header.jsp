<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="row justify-content-between my-3">
        <c:choose>
            <c:when test="${sessionScope.role == 'reader'}">
            <div class="col">
                <ul class="nav fs-4">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/library/mainPage"><fmt:message key="library"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/library/catalog"><fmt:message key="catalog"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/library/reader/cabinet"><fmt:message key="cabinet"/></a>
                    </li>
                </ul>
            </div>
            </c:when>
            <c:when test="${sessionScope.role == 'librarian'}">
            <div class="col">
                <ul class="nav fs-4">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/library/mainPage"><fmt:message key="library"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/library/librarian/orders"><fmt:message key="orders"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/library/librarian/users"><fmt:message key="readers"/></a>
                    </li>
                </ul>
            </div>
            </c:when>
            <c:when test="${sessionScope.role == 'admin'}">
            <div class="col">
                <ul class="nav fs-4">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/library/mainPage"><fmt:message key="library"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/library/admin/catalog"><fmt:message key="catalog"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/library/admin/workers"><fmt:message key="workers"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/library/admin/users"><fmt:message key="readers"/></a>
                    </li>
                </ul>
            </div>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>

        <div class="col-md-auto pt-1 pe-0">
            <a class="nav-link" href="?sessionLocale=en">en</a>
        </div>
        <div class="col-md-auto pt-1 ps-0 pe-2">
            <a class="nav-link" href="?sessionLocale=uk">ukr</a>
        </div>

        <div class="col-md-auto p-1">
            <form action="${pageContext.request.contextPath}/library/logout">
                <button type="submit" class="btn btn-outline-secondary"><fmt:message key="logout"/></button>
            </form>
        </div>
</body>
</html>
