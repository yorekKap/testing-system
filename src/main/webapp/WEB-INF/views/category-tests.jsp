<%@ include file="/resources/jspf/jsp-setup.jspf" %>
<fmt:message key="tutor.categories.tests.title" var="title"/>
<fmt:message key="tutor.categories.tests.category-title" var="categoryTitle"/>
<fmt:message key="tutor.categories.tests.no-tests" var="noTests"/>
<fmt:message key="tutor.categories.tests.create-new-test" var="createNewTest"/>
<fmt:message key="tutor.categories.tests.pass-test" var="passTest"/>
<fmt:message key="tutor.categories.tests.test-records" var="testRecords"/>


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
                <div id="category-tests">

                    <div class="row">
                        <s:se requiredRole="TUTOR">
                            <div class="col-md-3" rv-hide="data.isCategoryTitleEditable">
                                <span>${category.title}</span>
                                <i rv-on-click="controller.editCategoryTitle"
                                   class="fa fa-pencil" aria-hidden="true"></i>
                            </div>
                            <div class="col-md-3" rv-show="data.isCategoryTitleEditable">
                                <input type="text" rv-value="data.newCategoryTitle"
                                       rv-on-blur="controller.updateCategoryTitle"/>
                            </div>
                        </s:se>

                        <s:se requiredRole="STUDENT">
                            <div class="col-md-3">
                                    ${category.title}
                            </div>
                        </s:se>
                    </div>

                    <s:se requiredRole="TUTOR">
                        <a class="btn btn-default"
                           href="/tutor/category/test?categoryId=${category.id}">${createNewTest}</a>
                    </s:se>

                    <c:if test="${empty tests}">
                        <p>${noTests}</p>
                    </c:if>

                    <c:if test="${not empty tests}">
                        <ul>
                            <c:forEach var="test" items="${tests}" varStatus="loop">
                                <li>
                                    <s:se requiredRole="TUTOR">
                                        <div rv-hide="data.editableToggler | isEditable ${loop.index} ">
                                            <div>
                                                <a href="/tutor/category/test/records?testId=${test.id}"> ${test.title}</a>
                                                <i data-index="${loop.index}" rv-on-click="controller.editTestTitle"
                                                   class="fa fa-pencil" aria-hidden="true"></i>
                                                <i data-test-id="${test.id}" rv-on-click="controller.deleteTest"
                                                   class="fa fa-times" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                        <div rv-show="data.editableToggler | isEditable ${loop.index} ">
                                            <input type="text"
                                                   data-index="${loop.index}"
                                                   rv-on-blur="controller.updateTestTitle"
                                                   rv-value="data.editableToggler | editableTestTitle ${loop.index}">
                                        </div>
                                    </s:se>

                                    <s:se requiredRole="STUDENT">
                                        <div>
                                            <p>
                                                    ${test.title}
                                            </p>
                                        </div>
                                        <a class="btn btn-default"
                                           href="/student/category/tests/pass-test?testId=${test.id}">
                                                ${passTest}
                                        </a>
                                        <a class="btn btn-default"
                                           href="/student/category/tests/test-records?testId=${test.id}">
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
<script type="text/javascript">
    var categoryTitle = "${category.title}";
    var categoryId = ${category.id};

    var requestTestEdits = [];
    <c:forEach var="test" items="${tests}">

    requestTestEdits.push({
        isEditable: false,
        title: "${test.title}",
        id: ${test.id}
    });

    </c:forEach>
</script>
<script type="text/javascript" src="/resources/js/category-tests.js"></script>
</body>
</html>
