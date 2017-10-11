<%@ include file="/resources/jspf/jsp-setup.jspf" %>


<fmt:message key="tutor.categories.tests.new-test.test-title" var="testTitle"/>
<fmt:message key="tutor.categories.tests.new-test.add-new-question" var="addNewQuestion"/>
<fmt:message key="tutor.categories.tests.new-test.create-new-test" var="createNewTest"/>
<fmt:message key="tutor.categories.tests.new-test.back-to-category" var="backToCategory"/>
<fmt:message key="tutor.categories.tests.new-test.options" var="options"/>
<fmt:message key="tutor.categories.tests.new-test.option" var="option"/>
<fmt:message key="tutor.categories.tests.new-test.option-text" var="optionText"/>
<fmt:message key="tutor.categories.tests.new-test.question" var="question"/>
<fmt:message key="tutor.categories.tests.new-test.question-text" var="questionText"/>
<fmt:message key="tutor.categories.tests.new-test.question-mark" var="questionMark"/>
<fmt:message key="tutor.categories.tests.new-test.is-right" var="isRight"/>
<fmt:message key="tutor.categories.tests.new-test.add-option" var="addOption"/>
<fmt:message key="tutor.categories.tests.new-test.add-question" var="addQuestion"/>
<fmt:message key="tutor.categories.tests.new-test.error-message" var="errorMessage"/>



<html>
<head>
    <%@ include file="/resources/jspf/setup.jspf" %>
    <title>${title}</title>
</head>
<body>
<div id=wrapper class="toggled">
    <%@ include file="/resources/jspf/sidebar.jspf" %>


    <div id="page-content-wrapper">
        <div class="container-fluid" id="create-new-test">
            <form id="create-test-form" class="form-horizontal">
                <div class="form-group">
                    <div class="col-md-1">
                        <label class="control-label">${testTitle}</label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="test-title" name="testTitle"
                               placeholder="${testTitle}" required="true"
                               type="text" rv-value="data.testTitle"/>
                    </div>
                </div>
                <div clas="row">
                    <ol>
                        <li rv-each-question="data.questions">
                            { question.text } { question.mark }
                            <i class="fa fa-times" aria-hidden="true"
                               rv-on-click="controller.removeQuestion"></i>
                            <ul>
                                <li rv-each-option="question.options">
                                    { option.text } {option.isRight }
                                </li>
                            </ul>
                        </li>
                    </ol>
                </div>
                <div class="form-group">
                    <label class="col-md-1 control-label">${question}</label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="question-text"
                               name="questionText" placeholder="${questionText}" required="true"
                               rv-value="data.newQuestion.text"/>
                    </div>
                    <div class="col-md-4">
                        <input class="form-control" id="question-mark" name="questionMark"
                               placeholder="${questionMark}" required="true"
                               min="1" max="1000" type="number" rv-value="data.newQuestion.mark">
                    </div>
                    <div class="col-md-1">
                        <button rv-on-click="controller.addQuestion" type="button" class="btn btn-default addButton"><i
                                class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="row">
                    <ol>
                        <li rv-each-option="data.newQuestion.options">
                            { option.text } { option.isRight }
                            <i class="fa fa-times" aria-hidden="true"
                               rv-on-click="controller.removeOption"></i>
                        </li>
                    </ol>
                </div>
                <div class="form-group">
                    <label class="col-md-1 control-label" for="option-text">${option}</label>
                    <div class="col-md-4">
                        <input class="form-control col-md-4" type="text" id="option-text"
                               rv-value="data.newOption.text" required>
                    </div>
                    <div class="checkbox col-md-2">
                        <input id="is-right" class="checkbox-default" type="checkbox"
                               rv-checked="data.newOption.isRight">
                        <label for="is-right"> ${isRight}</label>
                    </div>
                    <div class="col-md-1">
                        <button rv-on-click="controller.addOption" type="button" class="btn btn-default addButton"><i
                                class="fa fa-plus"></i></button>
                    </div>
                </div>
                <button type="button"
                        class="btn btn-default"
                        rv-on-click="controller.createNewTest">${createNewTest}</button>
                <a type="button"
                   class="btn btn-default"
                   href="/tutor/category?categoryId=${sessionScope.currentCategoryId}">
                    ${backToCategory}
                </a>

            </form>
            <div id="error-message" hidden>${errorMessage}</div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var requestCategoryId = ${categoryId};
</script>
<script type="text/javascript" src="/resources/js/create-new-test.js"></script>
</body>
</html>
