<%@ include file="/resources/jspf/jsp-setup.jspf" %>

<fmt:message key="tutor.test-records.title" var="title"/>
<fmt:message key="tutor.test-records.username" var="username"/>
<fmt:message key="tutor.test-records.first-name" var="firstName"/>
<fmt:message key="tutor.test-records.last-name" var="lastName"/>
<fmt:message key="tutor.test-records.record-date" var="recordDate"/>
<fmt:message key="tutor.test-records.record-mark" var="recordMark"/>
<fmt:message key="tutor.test-records.no-test-records" var="noTestRecords"/>
<fmt:message key="tutor.test-records.search-for" var="searchFor"/>
<fmt:message key="tutor.test-records.searching-for" var="searchingFor"/>
<fmt:message key="tutor.test-records.no-entries-found" var="noEntriesFound"/>
<fmt:message key="tutor.test-records.show-test-record" var="showTestRecord"/>



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
                <div style="margin-right: 180px" id="pass-test">
                    <c:if test="${empty testRecords}">
                        ${noTestRecords}
                    </c:if>

                    <c:if test="${not empty testRecords}">
                        <form action="#" method="get">
                            <div class="input-group">
                                <input class="form-control" id="system-search" name="q" placeholder="${searchFor}"
                                       required>
                                <span class="input-group-btn">
                        <button type="submit" class="btn btn-default"><i
                                class="glyphicon glyphicon-search"></i></button>
                    </span>
                            </div>
                        </form>
                        <table style="margin-right: 10px" class="table table-list-search">
                            <thead>
                            <tr>
                                <th>${firstName}</th>
                                <th>${lastName}</th>
                                <th>${username}</th>
                                <th>${recordDate}</th>
                                <th>${recordMark}</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="testRecord" items="${testRecords}">
                                <tr>
                                    <td>${testRecord.student.firstName}</td>
                                    <td>${testRecord.student.lastName}</td>
                                    <td>${testRecord.student.username}</td>
                                    <td>${testRecord.date}</td>
                                    <td>${testRecord.mark}</td>
                                    <td>
                                        <a href="/tutor/category/test/test-record?testRecordId=${testRecord.id}">
                                                ${showTestRecord  }
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>
    <script type="text/javascript">
        var searchingFor = "${searchingFor}";
        var noEntriesFound = "${noEntriesFound}";
    </script>
    <script type="text/javascript" src="/resources/js/table-search.js"></script>
</body>
</html>

