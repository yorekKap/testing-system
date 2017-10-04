<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="utils" uri="mytags.com/utils" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="student.test-records.title" var="title"/>
<fmt:message key="student.test-records.date" var="date"/>
<fmt:message key="student.test-records.mark" var="mark"/>


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
                <div id="pass-test">
                    <ol>
                        <c:forEach var="testRecord" items="${testRecords}">
                            <a href="/student/category/tests/test-record?testRecordId=${testRecord.id}">
                                <div>
                                    ${date} : ${testRecord.date}
                                    ${mark} : ${testRecord.mark}
                                </div>
                            </a>
                        </c:forEach>
                    </ol>

                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>


</body>
</html>

