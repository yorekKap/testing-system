<%@ include file="/resources/jspf/jsp-setup.jspf" %>

<fmt:message key="student.pass-test.title" var="title"/>
<fmt:message key="student.pass-test.send-result" var="sendResult"/>

<html>
<head>
    <title>${title}</title>
    <%@ include file="/resources/jspf/setup.jspf" %>
    <link href="/resources/css/test-record.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper" class="toggled">

    <%@ include file="/resources/jspf/sidebar.jspf" %>

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="container">
                <div id="test-record" class="test-record">
                    <ol>
                        <c:forEach var="question" items="${questions}">
                            <li>
                                <p>${question.text} [${question.mark}]<p>
                                <ol>
                                    <c:forEach var="option" items="${question.options}">
                                        <li>
                                            <c:out value="${option.text}"/>
                                            <c:if test="${option.right}">
                                                <i class="fa fa-thumbs-up right-icon" aria-hidden="true"></i>
                                            </c:if>

                                            <c:if test="${not option.right}">
                                                <utils:contains list="${chosenOptionIds}"
                                                                value="${option.id}">
                                                    <i class="fa fa-thumbs-down wrong-icon" aria-hidden="true"></i>
                                                </utils:contains>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ol>
                            </li>
                        </c:forEach>
                    </ol>

                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>


</body>
</html>

