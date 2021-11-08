<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <c:import url="/WEB-INF/header.jsp"/>
    <div class="row ">
        <div class="col">
            <form action="${pageContext.request.contextPath}/library/admin/editBook">

                <div class="mb-3">
                    <input type="hidden" name="id" value="${requestScope.book.id}"/>
                    <label for="inputName" class="form-label"><fmt:message key="name"/></label>
                    <input type="text" required name="name" class="form-control" id="inputName"
                           pattern="[A-Za-zА-Яа-я]+" title="letters only code" value="${requestScope.book.name}">
                </div>
                <div class="mb-3">
                    <label for="inputAuthor" class="form-label"><fmt:message key="author"/></label>
                    <input type="text" required name="author" class="form-control" id="inputAuthor"
                           pattern="[A-Za-zА-Яа-я]+" title="<fmt:message key="lettersOnly"/> "value="${requestScope.book.author}">
                </div>
                <div class="mb-3">
                    <label for="inputPublisher" class="form-label"><fmt:message key="publisher"/></label>
                    <input type="text" required name="publisher" class="form-control" id="inputPublisher"
                           pattern="[A-Za-zА-Яа-я]+" title="<fmt:message key="lettersOnly"/>"value="${requestScope.book.publisher}">
                </div>
                <div class="mb-3">
                    <label for="inputPublishedTime" class="form-label"><fmt:message key="publisedTime"/></label>
                    <input type="date" required name="publishedTime" class="form-control" id="inputPublishedTime"
                           value="${requestScope.book.publishedTime}">
                </div>
                <div class="mb-3">
                    <label for="inputCount" class="form-label"><fmt:message key="count"/></label>
                    <input type="number" min="0" max="1000"  required  name="count" class="form-control"
                           id="inputCount" value="${requestScope.book.count}">
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
            </form>
        </div>

    </div>

</body>
</html>
