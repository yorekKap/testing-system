<%@ include file="/resources/jspf/jsp-setup.jspf" %>

<fmt:message key="student.pass-test.title" var="title"/>
<fmt:message key="student.pass-test.send-result" var="sendResult"/>


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
                        <c:forEach var="question" items="${questions}">
                            <li>
                                <p> ${question.text}<p>
                                <c:forEach var="option" items="${question.options}">
                                <div class="checkbox">
                                    <input id="${option.id}" type="checkbox" data-option-id="${option.id}"
                                           rv-on-click="controller.toggleOption">
                                    <label for="${option.id}">
                                            ${option.text}
                                    </label>
                                </div>
                                </c:forEach>
                            </li>
                        </c:forEach>
                    </ol>
                    <button type="button" class="btn btn-default" rv-on-click="controller.sendResults">
                        ${sendResult}
                    </button>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>


</body>
<script type="text/javascript">
    var requestTestId = ${testId};
</script>
<script type="text/javascript" src="/resources/js/pass-test.js"></script>
</html>
