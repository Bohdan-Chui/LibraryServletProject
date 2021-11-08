<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resource"/>

<html lang="${sessionScope.locale}">
<head>
    <title> <fmt:message key="catalog"/></title>
    <script>
        function sendForm(formId) {
            document.getElementById(formId).submit();
        }
    </script>
</head>
<body>

    <div class="container">
        <c:import url="/WEB-INF/header.jsp"/>
            <div class="row mx-3 pt-4 gx-5">
                <div class="col-9">
                    <div class="row">
                        <div class="col fs-5">
                            <fmt:message key="books"/>
                        </div>
                        <div class="col-md-auto">
                            <form id="sortForm" action="${pageContext.request.contextPath}/library/catalog">
                                <label for="sortSelect" class="fs-5"> <fmt:message key="sortBy"/></label>
                                <select name="options" id="sortSelect" onchange="sendForm('sortForm')">
                                    <option id="option0"
                                            value="">'-------'
                                    </option>
                                    <option id="option1" ${sessionScope.sortOption == "SortByName" ? "selected" : "" }
                                            value="SortByName"><fmt:message key="SortByName"/>
                                    </option>
                                    <option id="option2" ${sessionScope.sortOption == "SortByAuthor" ? "selected" : "" }
                                            value="SortByAuthor"><fmt:message key="SortByAuthor"/>
                                    </option>
                                    <option id="option3" ${sessionScope.sortOption == "SortByPublisher" ? "selected" : "" }
                                            value="SortByPublisher"><fmt:message key="SortByPublisher"/>
                                    </option>
                                    <option id="option4" ${sessionScope.sortOption == "SortByDate" ? "selected" : "" }
                                            value="SortByDate"><fmt:message key="SortByDate"/>
                                    </option>
                                </select>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
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

                                <c:forEach items="${list}" var="booklist">
                                    <tr>
                                        <td>${booklist.name}</td>
                                        <td>${booklist.author}</td>
                                        <td>${booklist.publisher}</td>
                                        <td>${booklist.count}</td>
                                        <td>${booklist.publishedTime}</td>

                                        <td>
                                            <form action="${pageContext.request.contextPath}/library/orderBook">
                                                <input type="hidden" name="bookId" value="${booklist.id}"/>
                                                <input type="hidden" name="count" value="${booklist.count}"/>
                                                <input type="hidden" name="place" value="home"/>
                                                <button class="btn btn-outline-dark btn-sm">
                                                    <fmt:message key="toHome"/>
                                                </button>
                                            </form>

                                        </td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/library/orderBook">
                                                <input type="hidden" name="bookId" value="${booklist.id}"/>
                                                <input type="hidden" name="count" value="${booklist.count}"/>
                                                <input type="hidden" name="place" value="library"/>
                                                <button class="btn btn-outline-dark btn-sm">
                                                    <fmt:message key="inLibrary"/>
                                                </button>
                                            </form>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-auto">
                            <%--For displaying Previous link except for the 1st page --%>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <c:if test="${requestScope.currentPage != 1}">
                                        <li>
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/library/catalog?page=${requestScope.currentPage - 1}"><fmt:message key="previous"/></a>
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
                                                       href="${pageContext.request.contextPath}/library/catalog?page=${i}">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <%--For displaying Next link --%>
                                    <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
                                        <td>
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/library/catalog?page=${requestScope.currentPage + 1}"><fmt:message key="next"/></a>
                                        </td>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>

        <div class="col">
            <form action="${pageContext.request.contextPath}/library/catalog">
                <div class="mb-3">
                    <label for="inputName" class="form-label"><fmt:message key="findByName"/></label>
                    <input type="text" name="name" class="form-control" id="inputName" placeholder="<fmt:message key="enterBookName"/>">
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
                <div class="mb-3">
                    <label for="inputPublisher" class="form-label">FindByPublisher</label>
                    <input type="text" name="publisher" class="form-control" id="inputPublisher" placeholder="<fmt:message key="enterPublisherName"/>">
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="search"/></button>
            </form>
        </div>
        </div>
    </div>
</body>
</html>
