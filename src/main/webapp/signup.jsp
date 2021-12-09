<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title><fmt:message key="signup"/></title>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-center pt-4 mt-3 fs-4">
        <fmt:message key="registration"/>
    </div>
    <div class="d-flex justify-content-center pt-3">
        <form action="${pageContext.request.contextPath}/library/signup">
            <div class="mb-3">
                <label for="inputPatronymic" class="form-label"><fmt:message key="signUp"/></label>
                <input type="text" required name="patronymic" class="form-control" id="inputPatronymic"
                       placeholder="<fmt:message key="enterPatronymic"/>" pattern="[A-Za-zА-Яа-я]+" title="letters only code" >
            </div>
            <div class="mb-3">
                <label for="inputFirstName" class="form-label"><fmt:message key="firstname"/></label>
                <input type="text" required name="firstname" class="form-control" id="inputFirstName"
                       placeholder="<fmt:message key="inputFirstName"/>" pattern="[A-Za-zА-Яа-я]+" title="letters only code" >
            </div>
            <div class="mb-3">
                <label for="inputSecondName" class="form-label"><fmt:message key="secondname"/></label>
                <input type="text" required name="secondname" class="form-control" id="inputSecondName"
                       placeholder="<fmt:message key="inputSecondname"/>" pattern="[A-Za-zА-Яа-я]+" title="letters only code" >
            </div>
            <div class="mb-3">
                <label for="inputEmail1" class="form-label"><fmt:message key="email"/></label>
                <input type="email" required  name="email" class="form-control" id="inputEmail1" placeholder="<fmt:message key="enterEmail"/>">
                <div id="emailHelp" class="form-text"><fmt:message key="safety"/></div>
            </div>
            <div class="mb-3">
                <label for="inputPassword1" class="form-label"><fmt:message key="inputPassword"/></label>
                <input type="password" required name="password" class="form-control" id="inputPassword1" placeholder="<fmt:message key="enterPassword"/>">
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
        </form>
    </div>
</div>
</body>
</html>
