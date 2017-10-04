<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="mytags.com/security" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="tutor.categories.tests.title" var="title"/>
<fmt:message key="tutor.categories.tests.no-tests" var="noTests"/>
<fmt:message key="tutor.categories.tests.create-new-test" var="createNewTest"/>
<fmt:message key="tutor.categories.tests.pass-test" var="passTest"/>
<fmt:message key="tutor.categories.tests.test-records" var="testRecords"/>


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
                <div id="tutor-category-tests">

                    <h2>${titles}</h2>

                    <s:se requiredRole="TUTOR">
                        <a class="btn btn-default"
                           href="/tutor/category/create-test?categoryId=${categoryId}">${createNewTest}</a>
                    </s:se>

                    <c:if test="${empty tests}">
                        <p>${noTests}</p>
                    </c:if>

                    <c:if test="${not empty tests}">
                        <ul>
                            <c:forEach var="test" items="${tests}">
                                <li>
                                    <p>
                                            ${test.title}
                                    </p>

                                    <s:se requiredRole="STUDENT">
                                        <a class="btn btn-default" href="/student/category/tests/pass-test?testId=${test.id}">
                                                ${passTest}
                                        </a>
                                        <a class="btn btn-default" href="/student/category/tests/test-records?testId=${test.id}">
                                                ${testRecords}
                                        </a>
                                    </s:se>
                                    <s:se requiredRole="TUTOR">
                                        <a href="/tutor/category/tests/show-test?testId=${test.id}">
                                        </a>
                                    </s:se>
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
<script src="/resources/js/tutor-categories.js"></script>
</html>
