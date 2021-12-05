<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
    <title><fmt:message key="library"/></title>

<body>

<div class="container">
    <c:import url="/WEB-INF/header.jsp"/>
    <div class="row ">
        <div class="col-9">
            <div class="row">
                <div class="col fs-5">
                    <fmt:message key="librarians"/>
                </div>
            </div>
            <table class="table">
                <tr>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="author"/></th>
                    <th><fmt:message key="publisher"/></th>
                    <th><fmt:message key="count"/></th>
                    <th><fmt:message key="publisedTime"/></th>
                    <th></th>
                    <th></th>
                </tr>

                <c:forEach items="${list}" var="list">
                    <tr>
                        <td>${list.name}</td>
                        <td>${list.author}</td>
                        <td>${list.publisher}</td>
                        <td>${list.count}</td>
                        <td>${list.publishedTime}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/library/admin/deleteBook">
                                <input type="hidden" name="id" value="${list.id}"/>
                                <button class="btn btn-outline-dark btn-sm">
                                    <fmt:message key="delete"/>
                                </button>
                            </form>

                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/library/admin/bookView">
                                <input type="hidden" name="id" value="${list.id}"/>
                                <button class="btn btn-outline-dark btn-sm">
                                    <fmt:message key="edit"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <div class="row">
                <div class="col-md-auto">
                    <%--For displaying Previous link except for the 1st page --%>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${requestScope.currentPage != 1}">
                                <li>
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/library/admin/catalog?page=${requestScope.currentPage - 1}"><fmt:message key="previous"/></a>
                                </li>
                            </c:if>

                            <%--For displaying Page numbers.
                            The when condition does not display a link for the current page--%>
                            <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${requestScope.currentPage eq i}">
                                        <li class="page-item active">
                                            <a class="page-link" aria-disabled="true">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/library/admin/catalog?page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <%--For displaying Next link --%>
                            <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
                                <td>
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/library/admin/catalog?page=${requestScope.currentPage + 1}"><fmt:message key="next"/></a>
                                </td>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <div class="col">
            <form action="${pageContext.request.contextPath}/library/admin/addBook">
                <div class="mb-3">
                    <label for="inputName" class="form-label"><fmt:message key="name"/></label>
                    <input type="text" required name="name" class="form-control" id="inputName"
                           placeholder="<fmt:message key="kobzar"/>"  pattern="[A-Za-zА-Яа-я]+" title="letters only code">
                </div>
                <div class="mb-3">
                    <label for="inputAuthor" class="form-label"><fmt:message key="author"/></label>
                    <input type="text" required name="author" class="form-control" id="inputAuthor"
                           placeholder="<fmt:message key="tarasShevchenko"/>" pattern="[A-Za-zА-Яа-я]+" title="letters only code">
                </div>
                <div class="mb-3">
                    <label for="inputPublisher" class="form-label"><fmt:message key="publisher"/></label>
                    <input type="text" required name="publisher" class="form-control" id="inputPublisher"
                           placeholder="<fmt:message key="Ukraine"/>" pattern="[A-Za-zА-Яа-я]+" title="letters only code">
                </div>
                <div class="mb-3">
                    <label for="inputPublishedTime" class="form-label"><fmt:message key="publisedTime"/></label>
                    <input type="date" required name="publishedTime" class="form-control" id="inputPublishedTime">
                </div>
                <div class="mb-3">
                <label for="inputCount" class="form-label"><fmt:message key="count"/></label>
                <input type="number" min="0" max="1000"  required  name="count" class="form-control"
                       id="inputCount" placeholder="0-1000">
            </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
            </form>
        </div>

    </div>

</body>
</html>
