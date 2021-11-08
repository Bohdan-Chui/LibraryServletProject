<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title><fmt:message key="workers"/></title>
</head>
<body>

<div class="container">
    <c:import url="/WEB-INF/header.jsp"/>
    <div class="row ">
        <div class="col-9">
            <div class="row">
                <div class="col fs-5">
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
                            <form action="${pageContext.request.contextPath}/library/admin/deleteLibrarian">
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

        <div class="col">
            <form action="${pageContext.request.contextPath}/library/admin/addWorker">
                <div class="mb-3">
                    <label for="inputPatronymic" class="form-label"><fmt:message key="patronymic"/></label>
                    <input type="text" required name="patronymic" class="form-control" id="inputPatronymic"
                           placeholder="<fmt:message key="enterPatronymic"/>"  pattern="[A-Za-zА-Яа-я]+" title="<fmt:message key="lettersOnly"/>">
                </div>
                <div class="mb-3">
                    <label for="inputFirstName" class="form-label"><fmt:message key="firstname"/></label>
                    <input type="text" required name="firstname" class="form-control" id="inputFirstName"
                           placeholder="<fmt:message key="inputFirstName"/>" pattern="[A-Za-zА-Яа-я]+" title="<fmt:message key="lettersOnly"/>">
                </div>
                <div class="mb-3">
                    <label for="inputSecondName" class="form-label"><fmt:message key="secondname"/></label>
                    <input type="text" required name="secondname" class="form-control" id="inputSecondName"
                           placeholder="<fmt:message key="inputSecondname"/>" pattern="[A-Za-zА-Яа-я]+" title="<fmt:message key="lettersOnly"/>">
                </div>
                <div class="mb-3">
                    <label for="inputEmail1" class="form-label"><fmt:message key="email"/></label>
                    <input type="email"required  name="email" class="form-control" id="inputEmail1" placeholder="<fmt:message key="enterEmail"/>">
                    <div id="emailHelp" class="form-text"><fmt:message key="safety"/></div>
                </div>
                <div class="mb-3">
                    <label for="inputPassword1" class="form-label"><fmt:message key="password"/></label>
                    <input type="password" required name="password" class="form-control" id="inputPassword1" placeholder="<fmt:message key="enterPassword"/>">
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
            </form>
        </div>

    </div>

</body>
</html>
