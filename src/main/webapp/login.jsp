<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="login"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="container">

    <div class="d-flex justify-content-center pt-3">
        <form action="${pageContext.request.contextPath}/library/login">
            <div class="mb-3">
                <label for="inputEmail1" class="form-label"><fmt:message
                        key="welcome"/></label>
                <input type="email" name="email" class="form-control" id="inputEmail1" placeholder="<fmt:message key="enterEmail"/>">
                <div id="emailHelp" class="form-text"><fmt:message key="safety"/></div>
            </div>
            <div class="mb-3">
                <label for="inputPassword1" class="form-label"><fmt:message key="password"/></label>
                <input type="password" name="password" class="form-control" id="inputPassword1" placeholder="<fmt:message key="enterPassword"/>">
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
        </form>
    </div>
</div>
</body>
</html>
