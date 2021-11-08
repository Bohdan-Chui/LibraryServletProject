<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message
        key="welcome"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


</head>
<body>
<div class="container">
    <div class="row justify-content-between my-3">
        <div class="col fs-4">
            <fmt:message key="library"/>
        </div>
        <div class="col-md-auto pt-1 pe-0">
            <a class="nav-link" href="?sessionLocale=en">en</a>
        </div>
        <div class="col-md-auto pt-1 ps-0 pe-2">
            <a class="nav-link" href="?sessionLocale=uk">ukr</a>
        </div><div class="col-md-auto p-1">


        <form action="${pageContext.request.contextPath}/login.jsp">
            <button type="submit" class="btn btn-outline-primary"><fmt:message key="login"/></button>
        </form>
    </div>
        <div class="col-md-auto p-1">
            <form action="${pageContext.request.contextPath}/signup.jsp">
                <button type="submit" class="btn btn-outline-secondary"><fmt:message key="signUp"/></button>
            </form>
        </div>
    <div class="row mx-3 pt-4 gx-5">
        <div class="d-flex justify-content-center fs-4"><fmt:message
                key="welcome"/></div>
        <div class="col-md-auto mt-4">
            <div class="fs-5">
                <fmt:message key="taskDescriprion"/>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
