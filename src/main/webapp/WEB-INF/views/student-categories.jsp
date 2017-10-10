<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="tutor.categories.title" var="title"/>
<fmt:message key="tutor.categories.no-categories" var="noCategories"/>
<fmt:message key="tutor.categories.new-category-title" var="newCategoryTitle"/>
<fmt:message key="tutor.categories.create-new-category" var="createNewCategory"/>
<fmt:message key="tutor.categories.open-to-all" var="openToAll"/>
<fmt:message key="tutor.categories.delete-category" var="deleteCategory"/>
<fmt:message key="tutor.categories.show-category-tests" var="showCategoryTests"/>


<html>
<head>
    <title>${title}</title>
    <%@ include file="/resources/jspf/setup.jspf" %>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper" class="toggled">

    <%@ include file="/resources/jspf/sidebar.jspf" %>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="container">
                <div id="student-categories">

                    <h2>${title}</h2>

                    <c:if test="${empty categories}">
                        <p>${noCategories}</p>
                    </c:if>

                    <c:if test="${not empty categories}">
                        <ul>
                            <c:forEach var="category" items="${categories}">
                                <li>
                                    <p>Category: ${category.title};
                                    </p>
                                    <a class="btn btn-default"
                                       href="/student/category/tests?categoryId=${category.id}">
                                            ${showCategoryTests}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>

            </div>

        </div>
    </div>
    <!-- /#page-content-wrapper -->
</div>


</body>
<script src="/resources/js/categories.js"></script>
</html>
