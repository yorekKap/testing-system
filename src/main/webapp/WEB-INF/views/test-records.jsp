<%@ include file="/resources/jspf/jsp-setup.jspf" %>

<fmt:message key="test-records.title" var="title"/>
<fmt:message key="test-records.date" var="date"/>
<fmt:message key="test-records.mark" var="mark"/>

<html>
<head>
    <title>${title}</title>
    <%@ include file="/resources/jspf/setup.jspf" %>
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

